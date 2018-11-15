//
//  LoginViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/12/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth

class LoginViewController: UIViewController {
    
    @IBOutlet weak var email_field: UITextField!
    @IBOutlet weak var password_field: UITextField!
    @IBOutlet weak var errorMsg: UILabel!
    @IBAction func login_button(_ sender: Any) {
        login()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func login() {
        if let email = self.email_field.text, let password = self.password_field.text {
            if email == "" || password == "" {
                print("Please Enter Text")
                self.errorMsg.text = "Please Enter Text"
            } else {
                Auth.auth().signIn(withEmail: email, password:  password) { (user, error) in
                    
                    if error == nil {
                        print ("You have successfully logged in")
                        self.performSegue(withIdentifier: "goFromLoginToDashboard", sender: self)
                        self.errorMsg.text = ""
                    } else {
                        print("Error")
                        self.errorMsg.text = "Invalid Credentials/ User Does not Exist"
                    }
                }
            }
        }
    }
}
