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
        /* Add this style to make the logo responsive */
        
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
    <!-- Navigation Bar -->
    <nav>
        <!-- Sale Dashboard Header -->
        <div class="dadhoboard">
            <div>
                <h3>Sale Dashboard</h3>
            </div>
            <div> 
            	<a class="logout btn btn-light" style="cursor:pointer; float: right; margin-top: 10px;  padding: 8px 15px; border-radius: 5px; " onclick="showLogoutConfirmation()">Logout</a>
        	</div>
        </div>
    </nav>

    <!-- Sidebar -->
    <div class="sidebar">
        <div class="first">
            <!-- Sidebar Menu Items -->
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
            <!-- View Products Section -->
            <div class="mt-4" style="width: 100%">
                <!-- Section Header -->
                <div class="m-auto bg-light rounded shadow text-dark p-3 d-flex justify-content-between" style="width: 95%">
                    <h4>View Products</h4>
                </div>
            </div>

            <!-- DataTable Section -->
            <div class="mt-4" style="width: 100%;">
                <div class="bg-light shadow rounded text-dark m-auto text-center border-radius-1" style="width: 95%;">
                    <!-- DataTable -->
                    <table class="table" id="productsTable">
                        <thead>
                            <!-- Table Headers -->
                            <tr>
                                <th>Name</th>
                                <th>Code</th>
                                <th>Detail</th>
                                <th>Original Price</th>
                                <th>Sold Price</th>
                                <th>Quantity</th>
                                <th>Image</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Data Rows -->
                             <c:forEach var="product" items="${products}">
                                <tr class="text-center">
                               
                                    <td>${product.name}</td>
                                    <td>${product.product_code}</td>
                                    <td>${product.product_detail}</td>
                                    <td>${product.original_price}</td>
                                    <td>${product.sold_price}</td>
                                    <td<c:choose>
                                            <c:when test="${product.product_qty <= 5}">
                                                class="text-danger fw-bold"
                                            </c:when>
                                        </c:choose>
                                      >
                                      	${product.product_qty}</td>
                                    <td>
                                        <!-- Display Image -->
                                        <img alt="ProfileImage" src="data:image/jpeg;base64,${product.product_image}" style="width:100px;height:60px;" />
                                    </td>
                                   
                                </tr>
                             </c:forEach>
                        </tbody>
                    </table>
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
					<button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="cancelLogout()">Cancel</button>
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

		function cancelLogout() {
    		$('#logoutModal').modal('hide');
		}
	
		function showModal(uId) {
            $('#deleteLink').attr('href', 'delete/' + uId);
            $('#deleteModal').modal('show');
        }

        function deleteItem() {
            $('#deleteModal').modal('hide');
        }

        function cancelDelete() {
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
                </script>
            </div>
        </div>
    </div>
</body>
</html>
