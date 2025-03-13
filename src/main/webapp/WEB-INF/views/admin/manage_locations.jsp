<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/12/2025
  Time: 7:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Location" %>
<html>
<head>
    <title>Manage locations</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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

        <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addLocationModal">
            Add New Location
        </button>

        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Location name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Location> locations = (List<Location>) request.getAttribute("locations");
                if (locations != null && !locations.isEmpty()) {
                    for (Location location : locations) {
            %>
            <tr>
                <td><%= location.getLocationId() %></td>
                <td><%= location.getLocationName() %></td>
                <td>
                    <a href="#" class="editLocationBtn"
                       data-location-id="<%= location.getLocationId() %>"
                       data-location-name="<%= location.getLocationName() %>"
                       data-toggle="modal" data-target="#editLocationModal">
                        Edit
                    </a>
                    <button class="btn btn-danger delete-location"
                            data-id="<%= location.getLocationId() %>">
                        Delete
                    </button>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="10">No Locations found.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>


<!-- Add Driver Modal -->
<div class="modal fade" id="addLocationModal" tabindex="-1" role="dialog" aria-labelledby="addLocationModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addLocationModalLabel">Add New Location</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="" method="post">
                    <input type="hidden" name="action" value="create">

                    <div class="form-group">
                        <label for="locationName">Location name :</label>
                        <input type="text" class="form-control" id="locationName" name="locationName" required>
                    </div>

                    <button type="submit" class="btn btn-success">Add Location</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Update Location Modal -->
<div class="modal fade" id="editLocationModal" tabindex="-1" role="dialog" aria-labelledby="editLocationModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editLocationModalLabel">Update Current Location</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="" method="post">
                    <form action="" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" id="editLocationId" name="locationId"> <!-- Add this hidden input -->

                        <div class="form-group">
                            <label for="editLocationName">Location name :</label>
                            <input type="text" class="form-control" id="editLocationName" name="locationName" required>
                        </div>

                        <button type="submit" class="btn btn-success">Update Location</button>
                    </form>
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
        $(".delete-location").click(function () {
            var locationId = $(this).data("id");

            if (!locationId) {
                alert("Error: Missing Location ID!");
                return;
            }

            if (confirm("Are you sure you want to delete this location?")) {
                $.ajax({
                    url: "/MegaCityCab_war_exploded/admin/manage_locations",
                    type: "DELETE",
                    contentType: "application/json",
                    data: JSON.stringify({ id: locationId }),
                    success: function (response) {
                        if (response.trim() === "success") {
                            alert("Location deleted successfully.");
                            location.reload();
                        } else {
                            alert("Failed to delete location.");
                        }
                    },
                    error: function () {
                        alert("Error deleting location.");
                    }
                });
            }
        });
    });
</script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const editButtons = document.querySelectorAll(".editLocationBtn");

        editButtons.forEach(button => {
            button.addEventListener("click", function () {
                document.getElementById("editLocationId").value = this.dataset.locationId;
                document.getElementById("editLocationName").value = this.dataset.locationName;

                $("#editLocationModal").modal("show");
            });
        });
    });
</script>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>
