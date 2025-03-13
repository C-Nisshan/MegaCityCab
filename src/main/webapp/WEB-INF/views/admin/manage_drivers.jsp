<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Driver, com.megacitycab.megacitycab.models.User" %>
<%
  List<Driver> driverList = (List<Driver>) request.getAttribute("driverList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Driver Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .main-container {
            display: flex;
        }
        .sidebar {
            width: 250px;
            flex-shrink: 0;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="main-container">
  <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>

  <div class="content">
    <h2>Driver Management</h2>

    <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addDriverModal">
      Add New Driver
    </button>

    <table class="table table-bordered">
      <thead class="thead-dark">
      <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Email</th>
        <th>NIC</th>
        <th>License Number</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<Driver> drivers = (List<Driver>) request.getAttribute("drivers");
        if (drivers != null && !drivers.isEmpty()) {
          for (Driver driver : drivers) {
            User user = driver.getUser();
      %>
      <tr>
        <td><%= driver.getDriverId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getName() %></td>
        <td><%= user.getAddress() %></td>
        <td><%= user.getTelephone() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getNic() %></td>
        <td><%= driver.getLicenseNumber() %></td>
        <td><%= driver.getStatus() %></td>
        <td>
          <a href="#" class="editDriverBtn"
             data-driver-id="<%= driver.getDriverId() %>"
             data-user-id="<%= user.getId()%>"
             data-username="<%= user.getUsername() %>"
             data-name="<%= user.getName() %>"
             data-address="<%= user.getAddress() %>"
             data-nic="<%= user.getNic() %>"
             data-telephone="<%= user.getTelephone() %>"
             data-email="<%= user.getEmail() %>"
             data-license-number="<%= driver.getLicenseNumber() %>"
             data-status="<%= driver.getStatus() %>"
             data-toggle="modal" data-target="#updateDriverModal">
            Edit
          </a>
            <button class="btn btn-danger delete-driver"
                    data-id="<%= driver.getDriverId() %>"
                    data-user-id="<%= user.getId() %>">
                Delete
            </button>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr>
        <td colspan="10">No drivers found.</td>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </div>
</div>

<!-- Add Driver Modal -->
<div class="modal fade" id="addDriverModal" tabindex="-1" role="dialog" aria-labelledby="addDriverModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addDriverModalLabel">Add New Driver</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="" method="post">
          <input type="hidden" name="action" value="create">
          <input type="hidden" name="role" value="Driver">

          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
          </div>

          <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
          </div>

          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
          </div>

          <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" required>
          </div>

          <div class="form-group">
            <label for="nic">NIC:</label>
            <input type="text" class="form-control" id="nic" name="nic" required>
          </div>

          <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" name="telephone" required>
          </div>

          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email">
          </div>

          <div class="form-group">
            <label for="licenseNumber">License Number:</label>
            <input type="text" class="form-control" id="licenseNumber" name="license_number" required>
          </div>

          <div class="form-group">
            <label for="status">Status:</label>
            <select class="form-control" id="status" name="status" required>
              <option value="Available">Available</option>
              <option value="Unavailable">Unavailable</option>
            </select>
          </div>

          <button type="submit" class="btn btn-success">Add Driver</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Update Driver Modal -->
<div class="modal fade" id="updateDriverModal" tabindex="-1" role="dialog" aria-labelledby="updateDriverModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateDriverModalLabel">Update Driver</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" id="editDriverId" name="driver_id">
                    <input type="hidden" id="editUserId" name="user_id">

                    <div class="form-group">
                        <label for="editUsername">Username:</label>
                        <input type="text" class="form-control" id="editUsername" name="username" required>
                    </div>

                    <div class="form-group">
                        <label for="editPassword">Password:</label>
                        <input type="password" class="form-control" id="editPassword" name="password" required>
                    </div>

                    <div class="form-group">
                        <label for="editName">Name:</label>
                        <input type="text" class="form-control" id="editName" name="name" required>
                    </div>

                    <div class="form-group">
                        <label for="editAddress">Address:</label>
                        <input type="text" class="form-control" id="editAddress" name="address" required>
                    </div>

                    <div class="form-group">
                        <label for="editNic">NIC:</label>
                        <input type="text" class="form-control" id="editNic" name="nic" required>
                    </div>

                    <div class="form-group">
                        <label for="editPhone">Phone:</label>
                        <input type="text" class="form-control" id="editPhone" name="telephone" required>
                    </div>

                    <div class="form-group">
                        <label for="editEmail">Email:</label>
                        <input type="email" class="form-control" id="editEmail" name="email">
                    </div>

                    <div class="form-group">
                        <label for="editLicenseNumber">License Number:</label>
                        <input type="text" class="form-control" id="editLicenseNumber" name="license_number" required>
                    </div>

                    <div class="form-group">
                        <label for="editStatus">Status:</label>
                        <select class="form-control" id="editStatus" name="status" required>
                            <option value="Available">Available</option>
                            <option value="Unavailable">Unavailable</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-success">Update Driver</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $(".delete-driver").click(function () {
            var driverId = $(this).data("id");
            var userId = $(this).data("user-id");

            if (!driverId || !userId) {
                alert("Error: Missing driver or user ID!");
                return;
            }

            if (confirm("Are you sure you want to delete this driver?")) {
                $.ajax({
                    url: "/MegaCityCab_war_exploded/admin/manage_drivers",
                    type: "DELETE",
                    contentType: "application/json",
                    data: JSON.stringify({ id: driverId, userId: userId }),
                    success: function (response) {
                        if (response.trim() === "success") {
                            alert("Driver deleted successfully.");
                            location.reload();
                        } else {
                            alert("Failed to delete driver.");
                        }
                    },
                    error: function () {
                        alert("Error deleting driver.");
                    }
                });
            }
        });
    });
</script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const editButtons = document.querySelectorAll(".editDriverBtn");

        editButtons.forEach(button => {
            button.addEventListener("click", function () {
                document.getElementById("editDriverId").value = this.dataset.driverId;
                document.getElementById("editUserId").value = this.dataset.userId;
                document.getElementById("editUsername").value = this.dataset.username;
                document.getElementById("editName").value = this.dataset.name;
                document.getElementById("editAddress").value = this.dataset.address;
                document.getElementById("editNic").value = this.dataset.nic;
                document.getElementById("editPhone").value = this.dataset.telephone;
                document.getElementById("editEmail").value = this.dataset.email;
                document.getElementById("editLicenseNumber").value = this.dataset.licenseNumber;
                document.getElementById("editStatus").value = this.dataset.status;

                $("#updateDriverModal").modal("show");
            });
        });
    });
</script>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>
