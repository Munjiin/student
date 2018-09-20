<%--
  Created by IntelliJ IDEA.
  User: 5CLASS-184
  Date: 2018-09-12
  Time: 오후 7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">understand</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">


			<c:forEach var="question" items="${question}" >
			<p class="panel panel-default">
			<div class="panel-heading">Q. ${question.question}</div>
			</c:forEach>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">

						<form method="post">

							<label class="radio-inline"> <input type="radio"
								name="reply" id="optionsRadiosInline1"
								value="1" checked>이해했서요!
							</label> <label class="radio-inline"> <input type="radio"
								name="reply" id="optionsRadiosInline2"
								value="0">이해못해써요ㅠㅠ
							</label>

					
							<div class="form-group">
								<label>Comment</label> <input name="cmt">
							</div>


							<button>보내기</button>
						</form>

					</div>
				</div>
				<!-- /.panel-body -->
			</div>



		<%@ include file="includes/footer.jsp"%>