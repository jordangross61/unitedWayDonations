//
//  ItemSizeViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/25/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseDatabase
import FirebaseAuth

class ItemSizeViewController: UIViewController,  UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var shortDes_field: UITextField!
    @IBOutlet weak var longDes_field: UITextField!
    @IBOutlet weak var value_field: UITextField!
    @IBOutlet weak var comments_field: UITextField!
    @IBOutlet weak var sizePicker: UIPickerView!
    @IBOutlet weak var errorMsg: UILabel!
    
    @IBAction func add_button(_ sender: Any) {
        addSizeItem()
        self.performSegue(withIdentifier: "goFromSizeToEmpDash", sender: self)
    }
    
    var pickerData: [String] = [String]()
    var sizePicked: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Connect data:
        self.sizePicker.delegate = self
        self.sizePicker.dataSource = self
        self.sizePicker.selectRow(0, inComponent: 0, animated: true)
        
        // input picker data
        pickerData = ["XS", "S", "M", "L", "XL"]
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
        sizePicked = pickerData[row]
    }
    
    func addSizeItem() {
        print("size item added")
        
        let now = Date()
        let formatter = DateFormatter()
        formatter.timeZone = TimeZone.current
        formatter.dateFormat = "yyyy-MM-dd HH:mm"
        let time = formatter.string(from: now)
        
        if let short = self.shortDes_field.text, let long = self.longDes_field.text, let val = self.value_field.text, let comments = self.comments_field.text {
            if short == "" || long == "" || val == "" || comments == "" {
                self.errorMsg.text = "Please Enter Text in All Fields"
                print("Error")
            } else {
                let userID = (Auth.auth().currentUser?.uid)
                let ref: DatabaseReference = Database.database().reference().child("users")
                
                ref.child(userID!).observeSingleEvent(of: .value, with: {
                    (snapshot) in
                    // Get user value
                    let value = snapshot.value as? NSDictionary
                    let locationId = value?["location"] as? String ?? ""
                
                let userRef: DatabaseReference = Database.database().reference().child("items")
                userRef.childByAutoId().setValue(
                    ["category": "CLOTHING",
                     "shortDescription": short,
                     "longDescription": long,
                     "time": time,
                     "value": val,
                     "comments": comments,
                     "size": self.sizePicked,
                     "locationId": locationId])
                })
            }
        }
    }
}
