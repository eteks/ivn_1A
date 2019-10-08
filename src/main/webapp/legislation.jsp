<%@include file="header.jsp" %>
<%@include file="sidebar.jsp" %>

<div class="pcoded-content" ng-controller="MyCtrl as Demo" ng-init="getAllCount()">    
    <div class="pcoded-inner-content">
        <div class="main-body">

            <div class="page-wrapper">
                <div class="page-header card">
                    <div class="row align-items-end">
                        <div class="col-lg-8">
                            <div class="page-header-title">
                                <i class="icofont icofont-automation bg-c-red"></i>
                                <div class="d-inline">
                                    <h4>Legislation</h4>
                                    <span>Listing, Assign</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="page-header-breadcrumb">
                                <ul class="breadcrumb-title">
                                    <li class="breadcrumb-item">
                                        <a href="index.html"> <i class="icofont icofont-home"></i> </a>
                                    </li>
                                    <li class="breadcrumb-item"><a href="#!">Legislation</a> </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="page-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                               <div class="card-block marketing-card p-t-20 row">
                                   <div class="col-md-12">
                                        <div class="card-header">
                                            <h5 class="card-header-text">Task </h5>
<%--                                            <h5 class="card-header-text">Task ${sessionScope.user.username}</h5>--%>
                                        </div>
                                       
                                       <div id="accordion" role="tablist" aria-multiselectable="true" ng-repeat="t in task" >
                                            <div class="accordion-panel" ng-if="t.pdb.completion_status == true">
                                                <div class="accordion-heading" role="tab" id="headingOne">
                                                    <h3 class="card-title accordion-title">
                                                        <a class="accordion-msg text-success" data-toggle="collapse"
                                                           data-parent="#accordion" href="#collapseOne"
                                                           aria-expanded="true" aria-controls="collapseOne">
                                                            View
                                                        </a>
                                                    </h3>
                                                </div>
                                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                                    <div class="accordion-content accordion-desc task_content">
                                                        <p><span>Name</span>: {{t.pdb.name}} </p>
                                                        <p><span>Created Date</span>: {{t.pdb.completion_date}} </p>
                                                        <p><span>Created By</span>: {{t.pdb.created_by}} </p>
                                                        <div class="text-center">
                                                            <a href="#" ng-click="task_accept()" class="btn-round btn btn-primary">Accept</a>&nbsp;&nbsp;
                                                            <a href="#" ng-click="task_reject()" class="btn-round btn btn-danger">Reject</a>
                                                        </div>
                                                        <p><span>Status</span>: 
                                                            <label ng-if="t.legislation.acceptance_status == 'yes'">Pending</label>
                                                            <label ng-if="t.legislation.acceptance_status == 'no'">rejected</label>
                                                            <label ng-if="t.legislation.completion_status == 'yes'">Completed</label>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                           <hr></hr>
                                        </div>
                                   </div>
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="legislation_combination.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>Legislation Rule Builder</span></br>
                                                        <span class="count">{{vehicleversion_count}}</span>
                                                        <i class="icofont icofont-tools-alt-2 text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="legislate_list.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>Legislation Version</span>
                                                        <span class="count">{{pdbversion_count}}</span>
                                                        <i class="icofont icofont-bag-alt text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>  
                                        </div>
                                    </div>
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">  
                                            <s:url action="legi_ver_create.action" var="aURL" />
                                            <s:a href="%{aURL}">     
                                                    <div class="card-block">
                                                        <span>Add Legislation Version</span>
                                                        <span class="count"> + </span>
                                                        <i class="icofont icofont-plus-square text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>
                                   
<!--                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="pdb_assign.action" var="aURL" />
                                            <s:a href="%{aURL}">      
                                                <div class="card-block">
                                                    <span> PDB Activity</span>
                                                    <span class="count"> + </span>
                                                    <i class="icofont icofont icofont-star-alt-2 text-c-red"></i>  
                                                    <div class="clearfix"></div>
                                                </div>
                                            </s:a>    
                                        </div>
                                    </div>-->
                                   
                                </div>
                            </div>
                        </div>
                                            
<%@include file="footer.jsp" %>
<script>
//        var app = angular.module('angularTable', []);

        app.controller('MyCtrl',function($scope, $http, $window, $location, $element, $rootScope)
        {
            $scope.task = [];
//            alert("MyCtrl");
//            $scope.getAllCount = function()
//            {
//                $http.get("dashboard.action").then(function(data, status, headers, config){
//                    var data = JSON.parse("<s:property value="count"/>".replace(/&quot;/g,'"'));
//                    $scope.vehicleversion_count = data['vehicleversion_count'];
//                    $scope.pdbversion_count = data['pdbversion_count'];
//                    $scope.pdbfeatures_count = data['pdbfeatures_count'];
//                });
//            }
//            $scope.task = {};
            $http({
                url: 'getTasks',
                method: "POST",
                data: {"froms":"Legislationversion"},
            }).then(function (response, status, headers, config){
                if(response.data.maps_string.success){
                    // $window.alert(JSON.stringify(response.data.maps_object));
                    // $window.alert(JSON.stringify(response.data.maps_object.tasks));
                    // console.log(JSON.stringify(response.data.maps_object.tasks))
                    $scope.task = response.data.list_object;
                    $window.alert(JSON.stringify($scope.task));
//                            $window.document.getElementById('model').focus();
                } else {
                    $window.alert(JSON.stringify(response.data.maps_string));
                }
            });
//$scope.task = 
//                            {
//                                "pdb": {
//                                    "name": "PDB version 2.5",
//                                    "completion_status": "true",
//                                    "accepted_date": "2019-10-03T12:33:49",
//                                    "accepted_by": "khan",
//                                    "completion_date": "2019-10-03T12:33:49",
//                                    "created_date": "2019-10-03T12:33:49",
//                                    "created_by": "khan",
//                                    "acceptance_status": "true"
//                                },
//                                    safety:
//                                    {
//                                        name: "safety version 1.0",
//                                        created_date: "2019-03-12 10:03:03",
//                                        created_by: "Anand",
//                                        acceptance_status: "",
//                                        accepted_date: "2019-03-12 10:03:03",
//                                        accepted_by: "Anand",
//                                        completion_status: "",
//                                        completion_date: "2019-03-12 10:03:03"
//                                    },
//                                    legislation:
//                                    {
//                                        name: "legislation version 1.0",
//                                        created_date: "2019-03-12 10:03:03",
//                                        created_by: "Anand",
//                                        acceptance_status: "",
//                                        accepted_date: "2019-03-12 10:03:03",
//                                        accepted_by: "Anand",
//                                        completion_status: "",
//                                        completion_date: "2019-03-12 10:03:03"
//                                    },
//                                    feature:
//                                    {
//                                        name: "legislation version 1.0",
//                                        created_date: "2019-03-12 10:03:03",
//                                        created_by: "Anand",
//                                        acceptance_status: "",
//                                        accepted_date: "2019-03-12 10:03:03",
//                                        accepted_by: "Anand",
//                                        completion_status: "",
//                                        completion_date: "2019-03-12 10:03:03"
//                                    },
//                                    ivn:
//                                    {
//                                        name: "ivn version 1.0",
//                                        created_date: "2019-03-12 10:03:03",
//                                        created_by: "Anand",
//                                        acceptance_status: "yes",
//                                        accepted_date: "2019-03-12 10:03:03",
//                                        accepted_by: "Anand",
//                                        completion_status: "",
//                                        completion_date: "2019-03-12 10:03:03"
//                                    },
//                                    acb:
//                                    {
//                                        name: "ivn version 1.0",
//                                        created_date: "2019-03-12 10:03:03",
//                                        created_by: "Anand",
//                                        acceptance_status: "",
//                                        accepted_date: "2019-03-12 10:03:03",
//                                        accepted_by: "Anand",
//                                        completion_status: "",
//                                        completion_date: "2019-03-12 10:03:03"
//                                    }
//                            };
                            $scope.task_accept = function()
                            {
                                alert(JSON.stringify('${sessionScope.user.username}'));
//                                $scope.task.legislation.acceptance_status="yes";
                            }
                            $scope.task_reject = function()
                            {
                                $scope.task.legislation.acceptance_status="no";
                            }
 
        });
    </script> 
     
</body>

</html>