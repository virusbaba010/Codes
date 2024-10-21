#include <iostream>
using namespace std;

int main() {
    cout << "\t\t~~VIRUS Bank~~" << endl;
    cout << "\t\tWelcome to the ATM Machine" << endl;
    cout << "PIN = 2580" << endl;

    int epin, pin = 2580, bal = 5000, attempt = 0;
    cout << "Please Enter your PIN: ";
    cin >> epin;

    bool atm;
    if (epin != pin) {
        cout << "Invalid PIN Please try again later" << endl;
        atm = false;
    } else {
        atm = true;
    }

    while (atm) {
        cout << "\t\t-----------" << endl;
        cout << "\t\t VIRUS ATM " << endl;
        cout << "\t\t-----------" << endl;

        cout << "Main Menu:" << endl;
        cout << "1> Check Balance" << endl;
        cout << "2> Deposit Money" << endl;
        cout << "3> Withdraw Money" << endl;
        cout << "4> Exit" << endl;

        int c;
        cout << "Enter choice: ";
        cin >> c;

        if (c == 1) {
            cout << "Your Balance is " << bal << endl;
        } else if (c == 2) {
            int dep;
            cout << "Enter Money you want to Deposit: ";
            cin >> dep;
            bal += dep;
            cout << dep << " You added successfully." << endl;
            cout << "You have " << bal << " now in your account." << endl;
        } else if (c == 3) {
            int wit;
            cout << "Enter Money you want to Withdraw: ";
            cin >> wit;
            if (bal < wit) {
                cout << "You don't have sufficient balance in account." << endl;
            } else {
                cout << "Successfully withdraw " << wit << "from account." << endl;
                bal -= wit;
                cout << "You have " << bal << " now in your account." << endl;
            }
        } else if (c == 4) {
            cout << "Thank you for using our VIRUS ATM." << endl;
            cout << "Exiting..." << endl;
            atm = false;
        } else {
            cout << "Invalid Choice. Please enter correct option" << endl;
            attempt++;
            if (attempt == 3) {
                atm = false;
                cout << "You have maximum attempt choosed." << endl;
            }
        }
    }

    return 0;
}