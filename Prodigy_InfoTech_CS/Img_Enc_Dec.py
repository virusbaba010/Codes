#first install Pillow from cmd to use module
#pip install Pillow
from PIL import Image
import random
import os

def e_img(image_path, output_path):
    try:
        with Image.open(image_path) as img:
            pixels = img.load()
            width, height = img.size

            random.seed(42)

            for x in range(width):
                for y in range(height):
                    x2, y2 = random.randint(0, width - 1), random.randint(0, height - 1)
                    pixels[x, y], pixels[x2, y2] = pixels[x2, y2], pixels[x, y]
            
            img.save(output_path)
        print("Image encryption successful.")
    except Exception as e:
        print(f"An error occurred during encryption: {e}")

def d_img(e_path, output_path):
    try:
        with Image.open(e_path) as img:
            pixels = img.load()
            width, height = img.size

            random.seed(42)

            swaps = []
            for x in range(width):
                for y in range(height):
                    x2, y2 = random.randint(0, width - 1), random.randint(0, height - 1)
                    swaps.append(((x, y), (x2, y2)))

            for ((x1, y1), (x2, y2)) in reversed(swaps):
                pixels[x1, y1], pixels[x2, y2] = pixels[x2, y2], pixels[x1, y1]
            
            img.save(output_path)
        print("Image decryption successful.")
    except Exception as e:
        print(f"An error occurred during decryption: {e}")

while True:
    operation = input("Would you like to 'encrypt' or 'decrypt' an image? (Type 'exit' to quit): ").strip().lower()
    if operation == 'exit':
        break
    
    image_path = input("Enter the path of the image file: ").strip()
    if not os.path.isfile(image_path):
        print("The specified file does not exist. Please check the path and try again.")
        continue
    
    output_path = input("Enter the path to save the output image: ").strip()

    if operation == 'encrypt':
        e_img(image_path, output_path)
    elif operation == 'decrypt':
     d_img(image_path, output_path)
    else:
        print("Invalid input. Please choose 'encrypt' or 'decrypt'.")
    
    continue_choice = input("Do you want to perform another operation? (yes/no): ").strip().lower()
    if continue_choice != 'yes':
        break
