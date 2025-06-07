import json
import os
import re
'''pip install pandas'''
import pandas as pd
'''pip install matplotlib'''
import matplotlib.pyplot as plt
from datetime import datetime
'''pip install scapy'''
from scapy.all import sniff

DANGEROUS_PORTS = [23, 445, 3389]
packet_log = []

# Protocol number to name mapping
PROTOCOL_MAP = {6: "TCP", 17: "UDP", 1: "ICMP"}

def packet_callback(packet):
    if packet.haslayer('IP'):
        src_ip = packet['IP'].src
        dst_ip = packet['IP'].dst
        proto_num = packet['IP'].proto
        protocol = PROTOCOL_MAP.get(proto_num, str(proto_num))
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

        payload = None
        if packet.haslayer('TCP') and packet.haslayer('Raw'):
            try:
                payload = packet['Raw'].load.decode(errors="ignore")
            except Exception:
                payload = None

        alert_message = None
        if packet.haslayer('TCP') and packet['TCP'].dport in DANGEROUS_PORTS:
            alert_message = f"⚠ WARNING: Suspicious activity detected on port {packet['TCP'].dport}"

        log_entry = {
            "timestamp": timestamp,
            "source_ip": src_ip,
            "destination_ip": dst_ip,
            "protocol": protocol,
            "payload": payload,
            "alert": alert_message
        }

        packet_log.append(log_entry)
        try:
            with open("packet_logs.json", "a") as file:
                json.dump(log_entry, file)
                file.write("\n")
        except Exception as e:
            print(f"Error writing to log file: {e}")

        # Print all details of the packet
        print("="*60)
        print(f"Timestamp      : {timestamp}")
        print(f"Source IP      : {src_ip}")
        print(f"Destination IP : {dst_ip}")
        print(f"Protocol       : {protocol}")
        if payload:
            print(f"Payload        : {payload}")
        if alert_message:
            print(f"ALERT          : {alert_message}")

print("Starting packet capture... (Press Ctrl+C to stop)")
sniff(prn=packet_callback, store=0)  # No count limit, runs until Ctrl+C

def analyze_packets():
    try:
        if not os.path.exists("packet_logs.json") or os.path.getsize("packet_logs.json") == 0:
            print("No packets to analyze.")
            return

        df = pd.read_json("packet_logs.json", lines=True)
        if df.empty:
            print("No packets to analyze.")
            return

        print("\nPacket Summary:")
        summary = df.groupby(['source_ip', 'destination_ip', 'protocol']).size().reset_index(name='count')
        print(summary)

        df["protocol"].value_counts().plot(kind="bar", title="Captured Packet Protocols")
        plt.xlabel("Protocol")
        plt.ylabel("Count")
        plt.tight_layout()
        plt.show()
    except Exception as e:
        print(f"Error analyzing packets: {e}")

analyze_packets()