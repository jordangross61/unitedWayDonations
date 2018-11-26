//
//  PickCategoryViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/25/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit

class PickCategoryViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var categoryPicker: UIPickerView!
    @IBAction func picker_button(_ sender: Any) {
        if (categoryPicked == "Clothing") {
            self.performSegue(withIdentifier: "goToItemSize", sender: self)
        } else {
            self.performSegue(withIdentifier: "goToItemNoSize", sender: self)
        }
    }
    
    var pickerData: [String] = [String]()
    var categoryPicked: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Connect data:
        self.categoryPicker.delegate = self
        self.categoryPicker.dataSource = self
        self.categoryPicker.selectRow(0, inComponent: 0, animated: true)
        
        // input picker data
        pickerData = ["Clothing", "Hat", "Kitchen", "Electronics", "Household", "Other"]
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToItemNoSize"{
            let vc = segue.destination as! ItemNoSizeViewController
            vc.categoryPicked = categoryPicked
        }
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
        categoryPicked = pickerData[row]
    }


}
