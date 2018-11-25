//
//  ItemNoSizeViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/25/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit

class ItemNoSizeViewController: UIViewController {

    @IBOutlet weak var shortDes_field: UITextField!
    @IBOutlet weak var longDes_field: UITextField!
    @IBOutlet weak var value_field: UITextField!
    @IBOutlet weak var comments_field: UITextField!
    
    @IBAction func add_button(_ sender: Any) {
        addItem()
        self.performSegue(withIdentifier: "goFromNoSizeToEmpDash", sender: self)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    func addItem() {
        print("add item")
    }
}
