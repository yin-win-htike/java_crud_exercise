<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Students</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="student" items="${students}">
				<tr>
					<td>
						${student.id}
					</td>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
					<td>${student.address}</td>
						<spring:url value="/students/${student.id}" var="studentUrl" />
						<spring:url value="/students/${student.id}/delete" var="deleteUrl" /> 
						<spring:url value="/students/${student.id}/update" var="updateUrl" />

					<td>	<button class="btn btn-info" onclick="location.href='${studentUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>