import re
import secrets
import math

GUESSES_PER_SECOND = 1_000_000_000  # 1 billion guesses per second

common_passwords = [
    "123456", "password", "123456789", "12345678", "12345",
    "1234567", "1234", "1234567890", "qwerty", "asdfgh"
]

def entropy(password):
    pool_size = 0
    if re.search(r"[a-z]", password):
        pool_size += 26
    if re.search(r"[A-Z]", password):
        pool_size += 26
    if re.search(r"[0-9]", password):
        pool_size += 10
    if re.search(r"[!@#$%^&*(),.?\":{}|<>]", password):
        pool_size += 32

    return math.log2(pool_size ** len(password)) if pool_size else 0

def time_to_crack(entropy_value):
    seconds = 2 ** entropy_value / GUESSES_PER_SECOND
    if seconds < 60:
        return f"{seconds:.2f} seconds"
    elif seconds < 3600:
        return f"{seconds / 60:.2f} minutes"
    elif seconds < 86400:
        return f"{seconds / 3600:.2f} hours"
    elif seconds < 31536000:
        return f"{seconds / 86400:.2f} days"
    else:
        return f"{seconds / 31536000:.2f} years"

def password_strength(password):
    length_criteria = len(password)
    uppercase_criteria = bool(re.search(r"[A-Z]", password))
    lowercase_criteria = bool(re.search(r"[a-z]", password))
    number_criteria = bool(re.search(r"[0-9]", password))
    special_char_criteria = bool(re.search(r"[!@#$%^&*(),.?\":{}|<>]", password))
    common_password_criteria = password not in common_passwords

    score = sum([length_criteria >= 8, uppercase_criteria, lowercase_criteria, number_criteria, special_char_criteria, common_password_criteria]) + int(length_criteria / 5)

    feedback = []
    if score < 5:
        feedback.append("Password could be improved:")
        if length_criteria < 8:
            feedback.append("- Increase the length to at least 8 characters.")
        if not uppercase_criteria:
            feedback.append("- Add uppercase letters.")
        if not lowercase_criteria:
            feedback.append("- Add lowercase letters.")
        if not number_criteria:
            feedback.append("- Include numbers.")
        if not special_char_criteria:
            feedback.append("- Add special characters.")
        if not common_password_criteria:
            feedback.append("- Avoid common passwords.")

    entropy_value = entropy(password)
    crack_time = time_to_crack(entropy_value)

    return {
        "Score": score,
        "Strength Description": "Very Strong" if score >= 7 else "Weak" if score < 4 else "Moderate",
        "Feedback": "\n".join(feedback) if feedback else "Password is very strong.",
        "Estimated Time to Crack": crack_time
    }

def generate_strong_password(length=12):
    characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()"
    return ''.join(secrets.choice(characters) for _ in range(length))

# Example Usage
while True:
    operation = input("Choose 'check' to evaluate password strength or 'generate' to create a strong password (or 'exit' to quit): ").strip().lower()
    
    if operation == 'exit':
        break
    elif operation == 'check':
        password = input("Enter the password to evaluate: ").strip()
        result = password_strength(password)
        
        print(f"Password Strength: {result['Strength Description']} (Score: {result['Score']})")
        print(f"Feedback: {result['Feedback']}")
        print(f"Estimated Time to Crack: {result['Estimated Time to Crack']}")
    elif operation == 'generate':
        generated_password = generate_strong_password()
        print(f"Generated Password: {generated_password}")
    else:
        print("Invalid input. Please choose 'check' or 'generate'.")
    
    continue_choice = input("Do you want to perform another operation? (yes/no): ").strip().lower()
    if continue_choice != 'yes':
        break
