#ATM Simulator

print("\t\t~~VIRUS Bank~~")
print("\t\tWelcome to the ATM Machine ")
print("PIN = 2580")

epin=int(input("Please Enter your PIN:"))
pin=2580
bal=5000
if epin!=pin:
    print("Invalid PIN Please try again later")
    atm = False
else:
    atm = True

attempt=0

while(atm):
    print("\t\t-----------")
    print("\t\t VIRUS ATM ")
    print("\t\t-----------")
    
    print("Main Menu:\n")
    print("1> Check Balance\n2> Deposit Money\n3> Withdraw Money\n4> Exit\n")
    c=int(input("Enter choice:"))

    if c==1:
        print("Your Balance is ",bal)
        
    elif c==2:
        dep=int(input("Enter Money you want to Deposit:"))
        bal += dep
        print(dep," You added successfully.")
        print("You have ",bal,"now in your account.")
        
    elif c==3:
        wit=int(input("Enter Money you want to Withdraw:"))
        if bal < wit:
            print("You donnot have sufficient balance in account.")
            
        else:
            print("Successfully withdraw ",wit,"from account.")
            bal -= wit
            print("You have ",bal,"now in your account.")
            
    elif c==4:
        print("Thank you for using our VIRUS ATM.")
        print("Exiting...")
        atm = False
        
    else:
        print("Invalid Choice. Please enter correct option")
        attempt+=1
        if attempt==3:
            atm = False
            print("You have maximux attempt choosed.")
