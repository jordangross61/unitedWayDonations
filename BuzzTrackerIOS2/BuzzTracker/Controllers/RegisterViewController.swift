//
//  RegisterViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/12/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth

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

    func register() {
        if let email = self.email_field.text, let password = self.password_field.text {
            if email == "" {
                print("Error")
            } else {
                
                Auth.auth().createUser(withEmail: email, password: password) { (user, error) in
                    if error == nil {
                        print ("You have successfully signed up")
                    } else {
                        print("Error")
                    }
                }
            }
        }
    }
}
