<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
	<link href="<c:url value="/resources/css/sucessful.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
 	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
	
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
	</style>

	<script>
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
    <div class="second d-flex justify-content-center align-items-center">
       <form:form class="p-5 shadow bg-white col-xl-5" action="/ConvenienceManagementSystem/products/add" method="post" modelAttribute="product" enctype="multipart/form-data">
    		<h2>Add Product Form</h2>
    		
    	<div>
			<form:label path="product_code" class="form-label">Product Code</form:label>
			<form:input path="product_code" class="form-control" readonly="readonly"/>
			<form:errors path="product_code" cssClass="text-danger"></form:errors>
		</div>
		
		<div>
			<form:label path="name" class="form-label">Product Name</form:label>
			<form:input path="name" class="form-control" placeholder="Please enter product name"/>
			<form:errors path="name" cssClass="text-danger"></form:errors>
		</div>
		
		<div>
			<form:label path="product_detail" class="form-label">Product Detail</form:label>
			<form:textarea path = "product_detail" class="form-control" placeholder="Please enter product detail"/>
			<form:errors path="product_detail" cssClass="text-danger"></form:errors>
		</div>
		
		<div>
			<form:label path="products_categories_id" class="form-label">Category</form:label>						
			<form:select  path="products_categories_id"  class="form-select  ">
				<form:option value="0">Select category</form:option>		 
				<c:forEach var="category" items="${categorys}">
     				<form:option value="${category.id}">${category.name}</form:option>
 				 </c:forEach>
			</form:select>
		</div>
		<div>
			<form:label path="original_price" class="form-label">Original Price(MMK)</form:label>
			<form:input path="original_price" type="number" class="form-control" id="originalPrice" placeholder="Please enter original price"/>
			<form:errors path="original_price" cssClass="text-danger"></form:errors>
		</div>
		
		<div>
			<form:label path="sold_price" class="form-label">Sold Price(MMK)</form:label>
			<form:input path="sold_price" type="number" class="form-control" id="soldPrice" placeholder="Please enter sold price"/>
			<form:errors path="sold_price" cssClass="text-danger"></form:errors>
		</div>
		
		<div>
			<form:label path="product_qty" class="form-label">Product Quantity</form:label>
			<form:input path="product_qty" type="number" class="form-control" placeholder="Please enter product quantity"/>
			<form:errors path="product_qty" cssClass="text-danger"></form:errors>
		</div>
		
		<div class="pb-2">
			<form:label path="multipart" class="form-label" >Product Image</form:label>
            <form:input type="file" path="multipart" class="form-control btn-success" required="required" placeholder="Please choose product image"/>
		</div>
		
		<div class="d-flex justify-content-end">
			<a class="btn btn-secondary" href="/ConvenienceManagementSystem/products/" style="margin-right:10px;">Cancel</a>
			<input type="submit" value="ADD Product" class="btn btn-primary">
		</div>
		
	</form:form>
		<div class="popup" id="popup">
         	<img src="https://www.shutterstock.com/image-vector/green-check-mark-icon-circle-260nw-576516469.jpg">
         	<h2>Thank You!</h2>
         	<p>Add Product Successfully</p>
         	<a href="/ConvenienceManagementSystem/products/"><button type="button">Ok</button></a>
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
     document.addEventListener("DOMContentLoaded", function () {
     	const originalPriceField = document.getElementById("originalPrice");
     	const soldPriceField = document.getElementById("soldPrice");
        const productQtyField = document.getElementsByName("product_qty")[0];
        const submitButton = document.querySelector('input[type="submit"]');

        submitButton.addEventListener('click', function (event) {
            const originalPrice = parseFloat(originalPriceField.value);
            const soldPrice = parseFloat(soldPriceField.value);
            const productQty = parseInt(productQtyField.value);

            if (soldPrice < originalPrice) {
                event.preventDefault();
                alert("Sold Price cannot be less than Original Price.");
            } else if (productQty > 500) {
                 event.preventDefault();
                 showProductQtyConfirmModal(event);
             }
        });

         function showProductQtyConfirmModal(event) {
             if (confirm("Product quantity is over 500. Are you sure you want to proceed?")) {
                document.querySelector('form').submit();
             } else {
                event.preventDefault();
              }
         }
      });

</script>
  <script>
  	const popup = document.getElementById("popup");
  	var result = 0;
  	result = ${result};
  	const openPopup = () => {
    	popup.classList.add("open-popup");
  	};

  	if (result === 1) {
    	openPopup();
  	}

 </script>
	
</body>
</html>