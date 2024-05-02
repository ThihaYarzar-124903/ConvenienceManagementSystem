<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff Home</title>
    <!-- Bootstrap CSS link -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <!-- Custom CSS for styling -->
    
    <style>
        /* Add this style to make the logo responsive */
        .logo {
            max-width: 15%;
            height: auto;
            display: block;
            margin: 0 auto; /* Center the logo */
        }
        
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
</head>
<body>
    <nav>
        <div class="dadhoboard">
           <h3>Sale Dashboard</h3>

            <div>
                <a class="logout btn btn-light" style="cursor:pointer; float: right; margin-top: 10px;  padding: 8px 15px; border-radius: 5px; " onclick="showLogoutConfirmation()">Logout</a>
            </div>
        </div>
    </nav>
    <div class="sidebar">
        <div class="first">
            <ul>
            	
                <li><a href="/ConvenienceManagementSystem/productDetails">Make Sale</a></li>
                <li><a href="/ConvenienceManagementSystem/viewproduct/">Products</a></li>
                <li><a href="/ConvenienceManagementSystem/categories/">Categories</a></li>
                <li><a href="/ConvenienceManagementSystem/salesreports">Generate Sale Report</a></li>
                <!-- Add more items as needed -->
            </ul>
        </div>
        <div class="second">
            <div style="width: 95%;" class="m-auto p-4 d-flex justify-content-between bg-light  shadow p-3 mb-2 rounded">
            		<p>Staff ID: ${loggedInUser.staff_id}</p>
                    <p>Welcome, ${loggedInUser.username}!</p>
                    <p>Email: ${loggedInUser.email}</p>
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

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>