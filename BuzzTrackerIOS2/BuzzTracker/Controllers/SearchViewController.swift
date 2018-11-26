//
//  SearchViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/14/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit

class SearchViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    

    @IBOutlet weak var categoryPicker: UIPickerView!
    @IBOutlet weak var locationField: UITextField!
    @IBOutlet weak var searchField: UITextField!
    
    @IBAction func searchButton(_ sender: Any) {
        performSegue(withIdentifier: "goToSearchResults", sender: self)
    }
    
    var categoryPickerData: [String] = [String]()
    var categoryPicked: String = ""

    override func viewDidLoad() {
        super.viewDidLoad()

        // Connect data:
        self.categoryPicker.delegate = self
        self.categoryPicker.dataSource = self
        self.categoryPicker.selectRow(0, inComponent: 0, animated: true)
        
        // input picker data
        categoryPickerData = ["All Categories", "Clothing", "Hat", "Kitchen", "Electronics", "Household", "Other"]
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToSearchResults"{
            let vc = segue.destination as! SearchResultsTableViewController
            vc.searchCategory = categoryPicked
            vc.searchLocation = self.locationField.text ?? ""
            vc.searchField = self.searchField.text ?? ""
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return categoryPickerData.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return categoryPickerData[row]
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1;
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        categoryPicked = categoryPickerData[row]
    }
}
