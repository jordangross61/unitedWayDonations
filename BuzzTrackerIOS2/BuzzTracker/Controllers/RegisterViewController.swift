//
//  RegisterViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/12/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase

class RegisterViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
 
    @IBOutlet weak var name_field: UITextField!
    @IBOutlet weak var email_field: UITextField!
    @IBOutlet weak var password_field: UITextField!
    @IBOutlet weak var user_picker: UIPickerView!
    
    @IBAction func register_button(_ sender: Any) {
        register()
    }
    
    var pickerData: [String] = [String]()
    var rightsPicked: String = ""

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Connect data:
        self.user_picker.delegate = self
        self.user_picker.dataSource = self
        self.user_picker.selectRow(0, inComponent: 0, animated: true)

        // input picker data
        pickerData = ["USER", "EMPLOYEE", "MANAGER", "ADMIN"]
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return pickerData.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return pickerData[row]
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1;
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        print(pickerData[row])
        rightsPicked = pickerData[row]
    }
    
    func register() {
        let username = self.name_field.text
        
        if let email = self.email_field.text, let password = self.password_field.text {
            if email == "" {
                print("Error")
            } else {
                Auth.auth().createUser(withEmail: email, password: password) { (user, error) in

                    if error == nil {
                        let userID = (Auth.auth().currentUser?.uid)
                        print(userID)
                        let ref: DatabaseReference = Database.database().reference()
                        
                        if (self.rightsPicked == "EMPLOYEE") {
                            ref.child("users").child(userID!).setValue(
                                ["email": email,
                                 "name": username,
                                 "password": password,
                                 "rights": self.rightsPicked])
                            self.performSegue(withIdentifier: "goToEmployeeLocationPicker", sender: self)
                        } else {
                            ref.child("users").child(userID!).setValue(
                                ["email": email,
                                 "name": username,
                                 "password": password,
                                 "rights": self.rightsPicked])
                            self.performSegue(withIdentifier: "goFromRegistrationToDashboard", sender: self)
                        }
                        
                        
                    } else {
                        print("\(String(describing: error?.localizedDescription))")
                    }
                }
            }
        }
        

    }
}
