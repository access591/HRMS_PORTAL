
 function AadharValidate() {
        var aadhar = document.getElementById("txtAadhar").value;
        var adharcardTwelveDigit = /^\d{12}$/;
        var adharSixteenDigit = /^\d{16}$/;
        if (aadhar != '') {
            if (aadhar.match(adharcardTwelveDigit)) {
            lblAadharCard.style.visibility = "hidden";
                
            }
            else if (aadhar.match(adharSixteenDigit)) {
            lblAadharCard.style.visibility = "hidden";
                
            }
            else {
            	
            	lblAadharCard.style.visibility = "visible";
               
            }
        }
    }


