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
    @IBOutlet weak var locationPicker: UIPickerView!
    @IBOutlet weak var searchField: UITextField!
    
    @IBAction func searchButton(_ sender: Any) {
        performSegue(withIdentifier: "goToSearchResults", sender: self)
    }
    
    var categoryPickerData: [String] = [String]()
    var locationPickerData: [String] = [String]()
    var categoryPicked: String = ""
    var locationPicked: Int = 0

    override func viewDidLoad() {
        super.viewDidLoad()

        // Connect data:
        self.categoryPicker.delegate = self
        self.categoryPicker.dataSource = self
        self.categoryPicker.selectRow(0, inComponent: 0, animated: true)
        
        // Connect data:
        self.locationPicker.delegate = self
        self.locationPicker.dataSource = self
        self.locationPicker.selectRow(0, inComponent: 0, animated: true)
        
        // input picker data
        categoryPickerData = ["All Categories", "Clothing", "Hat", "Kitchen", "Electronics", "Household", "Other"]
        locationPickerData = ["All Locations", "1", "2", "3", "4", "5", "6"]
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToSearchResults"{
            let vc = segue.destination as! SearchResultsTableViewController
            vc.searchCategory = categoryPicked
            vc.searchLocation = locationPicked
            vc.searchField = self.searchField.text ?? ""
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if (pickerView.tag == 0){
            return categoryPickerData.count
        } else {
            return locationPickerData.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if (pickerView.tag == 0){
            return categoryPickerData[row]
        } else {
            return locationPickerData[row]
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1;
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if (pickerView.tag == 0){
            categoryPicked = categoryPickerData[row]
        } else {
            if (locationPickerData[row] == "All Locations") {
                locationPicked = 0
            } else {
                locationPicked = row
            }
        }
    }
    
}
