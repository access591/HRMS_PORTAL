function leaveEmployeDisplay(){
	
	 var d = document.getElementById("employeeDropDownLeave");
	 
	var dValue = document.getElementById("employeeDropDownLeave").value;
	
	console.log(" d value is :"+ dValue );
   
	 var displayItem = d.options[d.selectedIndex].text;
	 
	 document.getElementById("hiddenField").value = displayItem;
	 
	document.getElementById("leaveRequestEmpId").value = dValue;
	
    console.log(displayItem);

    ajaxRequestLeave(dValue);
}


function ajaxRequestLeave(val){
	var empCode = val;
    console.log("ajax request : "+ empCode);
    const xhr = new XMLHttpRequest();
    xhr.open('POST','empPayDetail/'+empCode,true);
    xhr.getResponseHeader('content-type','application/json');
    xhr.onload = function(){
        if(this.status===200){
            let obj = JSON.parse(this.responseText);
            console.log(obj);

			var deptCode = obj.departmentCode;
			
			console.log(" depARTMENT CODE :"+	deptCode);
			
			$('#leaveDepartmentId').val( obj.departmentCode); 
			
			ajaxRequestForDepartmentLeave(deptCode);
        }
        else{
            console.error("some error occured");
        }
	
    }
    xhr.send();
}

function ajaxRequestForDepartmentLeave(val){
	var deptCode = val;
    console.log("ajax request : "+ deptCode);
    var xhr1 = new XMLHttpRequest();
    xhr1.open('POST','getdepartment/'+deptCode,true);
    xhr1.getResponseHeader('content-type','application/json');
	xhr1.response = 'json';
    xhr1.onload = function(){
        if(this.status===200){
		
			console.log("successfull");
             let obj1 = JSON.parse(this.responseText);
            console.log(obj1);

			$('#leaveDepartmentName').val(obj1.deptName); 
        }
        else{
            console.error("some error occured");
        }
    }
    xhr1.send();
}

var leaveTypeId = "";
var fromDateType = "f";

function leaveType(){
	
	leaveTypeId =  document.getElementById("leave_type_id").value;
	//console.log("type of leave : "+leaveType);
	
	if(leaveTypeId=="single"){
		console.log(" single leave type");
		document.getElementById("to_date_type").disabled = true;
		document.getElementById("to_date").disabled = true;
		document.getElementById("to_date").value = document.getElementById("from_date").value;
		countLeaveDays();
	}																

	else{
		console.log("multiple leave type ");
		document.getElementById("to_date_type").disabled = false;
		document.getElementById("to_date").disabled = false;
		
		countLeaveDays();

	}
}
	

function changeToDate(val){
	console.log(" dateChange function" + val);
	console.log("leave type : "+ leaveTypeId);
	if(leaveTypeId == "single"){
		document.getElementById("to_date").value = val;
		//document.getElementById("to_date").disabled = true;
	}
	if(leaveTypeId == "multiple"){
		countLeaveDays();
	}
}

function dayType(){
	fromDateType = document.getElementById("from_date_type").value;
	
	fromDateType = fromDateType;
	console.log(fromDateType + " in day type ");
	
	countLeaveDays();
}

function countLeaveDays(){

	console.log("leave type id : "+ leaveTypeId);
	console.log("from date type : "+ fromDateType);

	if(leaveTypeId == "single" && fromDateType =="f" )
	{
		document.getElementById("leave_for").value = 1 ;
		console.log("1st block");
	}
	if(leaveTypeId == "single" && fromDateType =="h" )
	{
		document.getElementById("leave_for").value = 0.5 ;
		console.log("2nd block");
	}
	if(leaveTypeId == "" && (fromDateType =="h" || fromDateType == "f") )
	{
		document.getElementById("leave_for").value = 1 ;
		console.log("3rd block");
	}
	if(leaveTypeId == "multiple" ){
		var fromDate = document.getElementById("from_date").value;
		var toDate = document.getElementById("to_date").value;
		
		var date1 = new Date(fromDate);
		var date2 = new Date(toDate);

		

		const diffTime = Math.abs(date2 - date1);
		const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));  

		console.log(" day diff. : "+ diffDays);
		document.getElementById("leave_for").value = diffDays ;

		
		console.log("calculate days : "+ totalLeave);
		//document.getElementById("leave_for").value = 0;
		console.log("4th block : from date "+ fromDate);
		
	}


}
