function leaveEmployeDisplay(){
	
	 var d = document.getElementById("employeeDropDownLeave");
	var dValue = document.getElementById("employeeDropDownLeave").value;
	
	console.log(" d value is :"+ dValue );
   
	 var displayItem = d.options[d.selectedIndex].text;

	document.getElementById("leaveRequestEmpId").value = dValue;
	
    console.log(displayItem);

    ajaxRequestLeave(dValue);
}


function ajaxRequestLeave(val){
	var empCode = val;
    console.log("ajax request : "+ empCode);
    const xhr = new XMLHttpRequest();
    xhr.open('POST','http://localhost:8181/empPayDetail/'+empCode,true);
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
    xhr1.open('POST','http://localhost:8181/departmentMaster/'+deptCode,true);
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
}

function dayType(){
	fromDateType = document.getElementById("from_date_type").value;
	
	fromDateType = fromDateType;
	console.log(fromDateType + " in day type ");
	
	countLeaveDays();
}

function countLeaveDays(){

	console.log("count leave days blocks ");
	if(leaveTypeId == "single" && fromDateType =="f" )
	{
		document.getElementById("leave_for").value = 1 ;
		console.log("single / f type ");
	}
	if(leaveTypeId == "single" && fromDateType =="h" )
	{
		document.getElementById("leave_for").value = 0.5 ;
		console.log("single / h type ");
	}
	if(leaveTypeId == "" && (fromDateType =="h" || fromDateType == "f") )
	{
		document.getElementById("leave_for").value = 1 ;
		console.log("blank / h or f type ");
	}
	console.log(" f case : "+ fromDateType + " and " + leaveTypeId);


}
