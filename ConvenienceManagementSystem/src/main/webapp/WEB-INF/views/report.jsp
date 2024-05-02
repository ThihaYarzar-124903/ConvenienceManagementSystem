<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sale Details</title>
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
			<h3>Sale Dashboard</h3> 	
		</div>	
		<div>	
        	<a class="logout btn btn-light" style="cursor:pointer; float: right; margin-top: 10px;  padding: 8px 15px; border-radius: 5px; " onclick="showLogoutConfirmation()">Logout</a>
        </div>
	</div>
	</nav>
	<div class="sidebar">
		<div class="first">
			<ul>
				<li><a href="/ConvenienceManagementSystem/staff">Dashboard</a></li>
				<li><a href="/ConvenienceManagementSystem/productDetails">Make Sale</a></li>
           		<li><a href="/ConvenienceManagementSystem/viewproduct/">Products</a></li>
           		<li><a href="/ConvenienceManagementSystem/categories/">Categories</a></li>
           		<li><a href="/ConvenienceManagementSystem/salesreports">Generate Sale Report</a></li>
					<!-- Add more items as needed -->
			</ul>
		</div>
		<div class="second">
			<div class="bg-white pb-4 mt-3 m-auto" style="width: 95%;">
    			<h3 class="bg-light  shadow p-3 mb-2 rounded">Sale Details</h3>
    				<table class="table" id="productsTable">
        				<tr>
            				<th>Product Name</th>
            				<th>Discount Percent</th>
            				<th>Quantity</th>
        				</tr>
        			<c:forEach items="${saleDetails.products}" var="product">
            			<tr>
                			<td>${product.productName}</td>
                			<td>${product.discountPercent} %</td>
                			<td>${product.qty}</td>
            			</tr>
        			</c:forEach>
    			</table>
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
</body>
</html>
