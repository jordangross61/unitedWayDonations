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
        performSegue(withIdentifier: "goFromRegistrationToDashboard", sender: self)
    }
    
    var pickerData: [String] = [String]()
    var rights: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Connect data:
        self.user_picker.delegate = self
        self.user_picker.dataSource = self
        
        // input picker data
        pickerData = ["User", "Employee", "Manager", "Admin"]
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
    
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if(row == 0)
        {
            self.rights = "User"
        }
        else if(row == 1)
        {
            self.rights = "Employee"
        }
        else if(row == 2)
        {
            self.rights = "Manager"
        }
        else
        {
            self.rights = "Admin"
        }
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
                        let ref: DatabaseReference = Database.database().reference()
                        
                        ref.child("users").child(userID!).setValue(
                            ["email": email,
                            "name": username,
                            "password": password,
                            "rights": self.rights])
                        
                        self.performSegue(withIdentifier: "goFromRegistrationToDashboard", sender: self)
                        
                    } else {
                        print("\(String(describing: error?.localizedDescription))")
                    }
                }
            }
        }
        
        
    }
}
