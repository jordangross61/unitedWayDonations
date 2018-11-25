//
//  ItemSizeViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/25/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit

class ItemSizeViewController: UIViewController,  UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var shortDes_field: UITextField!
    @IBOutlet weak var longDes_field: UITextField!
    @IBOutlet weak var value_field: UITextField!
    @IBOutlet weak var comments_field: UITextField!
    @IBOutlet weak var sizePicker: UIPickerView!
    
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
    }

}
