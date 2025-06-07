def Cipher(text, shift, direction='encrypt'):
    result = ""
    
    # Adjust shift value for decryption
    if direction == 'decrypt':
        shift = -shift
    
    for char in text:
        if char.isupper():
            result += chr((ord(char) + shift - 65) % 26 + 65)
        elif char.islower():
            result += chr((ord(char) + shift - 97) % 26 + 97)
        else:
            result += char
    
    return result

while True:
    message = input("Enter your message: ")
    shift = int(input("Enter shift value: "))
    action = input("Type 'encrypt' to encrypt or 'decrypt' to decrypt: ").strip().lower()
    if action == 'encrypt':
        e_text = Cipher(message, shift, 'encrypt')
        print(f"Encrypted Message: {e_text}")
    elif action == 'decrypt':
        d_text = Cipher(message, shift, 'decrypt')
        print(f"Decrypted Message: {d_text}")
    else:
        print("Invalid option. Please type 'encrypt' or 'decrypt'.")
    
    choice = input("Do you want to perform another operation? (yes/no): ").strip().lower()
    if  choice != 'yes':
        break
