<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Display Products</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.html5.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.70/pdfmake.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.70/vfs_fonts.js"></script>
    
    <style>
    .logout:hover{
        background-color:red;
        cursor:pointer;
    }
    .form-control {
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s ease;
    }
    .form-control:focus {
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3), 0 0 10px rgba(70, 130, 180, 0.6);
    }
    
    .name{
    	text-decoration:none;
    }
	</style>

</head>

<body>
	<nav>
     	<div class="dadhoboard">
      		<div>
           		<h3>Owner Dashboard</h3>
        	</div>
    
    		  <div> 
            	<a class="logout btn btn-light" style="cursor:pointer; float: right; margin-top: 10px;  padding: 8px 15px; border-radius: 5px; " onclick="showLogoutConfirmation()">Logout</a>
        	 </div>
      </div>
   </nav>
  
	<div class="sidebar">
    	<div class="first">
    		<ul>
    			<li><a href="/ConvenienceManagementSystem/owner/">Dashboard</a></li>
        		<li><a href="/ConvenienceManagementSystem/users/">Staff Management</a></li>
        		<li><a href="/ConvenienceManagementSystem/categorys/">Category Management</a></li>
        		<li><a href="/ConvenienceManagementSystem/products/">Product Management</a></li>
        		<li><a href="/ConvenienceManagementSystem/discounts/">Discount Management</a></li>
        		<li><a href="/ConvenienceManagementSystem/details">Manage Sales Report</a></li>
        <!-- Add more items as needed -->
       		</ul>
    	</div>
    	<div class="second">
    		<div class="mt-4" style="width: 100%">
    			<div class="m-auto rounded shadow bg-light text-dark p-3  d-flex justify-content-between" style="width: 95%">
	    			<h4>Manage Discount</h4>
	    		    <a  class="btn btn-success" href="add">Add Discount</a>
    		    </div>
    	    </div>
    	
     		<div class="mt-4 " style="width: 100%">
    			<div class="rounded shadow m-auto bg-light text-dark" style="width: 95%">	
 					<table id="productsTable">
  						<thead>
    						<tr>
      							<th scope="col">Name</th>
      							<th scope="col">Product Name</th>
      							<th scope="col">Discount Percent</th>
      							<th scope="col">From Date</th>
      							<th scope="col">To Date</th>
      							<th scope="col"></th>
    						</tr>
  						</thead>
   
  						<tbody>
  							<c:forEach var="discount" items="${discounts}">
   								<tr>
      								<td scope="row">${discount.name}</td>
      								<td><a href="/ConvenienceManagementSystem/products/" class="name">${discount.product_names}</a></td>
      								<td>${discount.discount_percent} %</td>
      								<td>${discount.from_date}</td>
      								<td>${discount.to_date}</td>
      								<td>
        								<div>
		  									<a href="edit/${discount.id}" class="btn"><i class="fa-solid fa-pen"></i></a>
		  									<a href="#" onclick="showModal('${discount.id}');" style="color:white" class="btn"><i class="fa-solid fa-trash"></i></a>
										</div>
											 <div class="modal" tabindex="-1" role="dialog" id="deleteModal"> 
    											<div class="modal-dialog" role="document"> 
        											<div class="modal-content">
            											<div class="modal-header">
                											<h5 class="modal-title">Delete Confirmation</h5>
               												<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
            											</div>
            											<div class="modal-body">
                											<p>Are you sure you want to delete?</p>
            											</div>
            											<div class="modal-footer">
                											<a id="deleteLink" href="#" class="btn btn-danger" onclick="deleteItem()">Delete</a>
                											<button type="button" class="btn btn-secondary" onclick="Delete()">Cancel</button>
           												 </div>
        											</div>
    											</div>
											</div>
	   									</td>
    								</tr>
     							</c:forEach> 
  							</tbody>
					</table>
	      		</div>
      		</div> 
    	</div>
 	</div>
 	<div class="modal" tabindex="-1" role="dialog" id="logoutModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Logout Confirmation</h5>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to logout?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-primary" onclick="logout()">Logout</a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="cancelDelete()">Cancel</button>
            </div>
        </div>
    </div>
</div>
 	
 	
 <script>
 function showModal(uId) {
     $('#deleteLink').attr('href', 'delete/' + uId);
     $('#deleteModal').modal('show');
 }

 function deleteItem() {
     $('#deleteModal').modal('hide');
 }

 function Delete() {
     $('#deleteLink').attr('href', '#');
     $('#deleteModal').modal('hide');
 }

 $(document).ready(function () {
     $('#productsTable').DataTable({
         dom: 'Bfrtip',
         buttons: [
             {
                 extend: 'excel',
                 exportOptions: {
                     columns: [0, 1, 2, 3, 4, 5, 6]
                 },
                 customize: function (xlsx) {
                     var sheet = xlsx.xl.worksheets['sheet1.xml'];
                     $('row c[r^="F"]', sheet).attr('s', '2');
                 }
             },
             {
                 extend: 'pdf',
                 exportOptions: {
                     columns: [0, 1, 2, 3, 4, 5, 6]
                 },
                 customize: function (doc) {
                     doc.styles.tableHeader.fillColor = '#007BFF';
                 }
             },
             'print'
         ]
     });
 });
 

 function showLogoutConfirmation() {
     $('#logoutModal').modal('show');
 }

 function logout() {
     window.location.href = "/ConvenienceManagementSystem/logout/";
 }

 function cancelDelete() {
     $('#logoutModal').modal('hide');
 }
</script>

</body>
</html>