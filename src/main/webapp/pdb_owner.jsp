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
                                    <h4>PDB Owner</h4>
                                    <span>Vehicle,Domain and Features</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="page-header-breadcrumb">
                                <ul class="breadcrumb-title">
                                    <li class="breadcrumb-item">
                                        <a href="index.html"> <i class="icofont icofont-home"></i> </a>
                                    </li>
                                    <li class="breadcrumb-item"><a href="#!">PDB</a> </li>
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
<!--                                   <div class="col-md-12">
                                        <div class="card-header">
                                            <h5 class="card-header-text">Task</h5>
                                        </div>
                                       <div id="accordion" role="tablist" aria-multiselectable="true">
                                            <div class="accordion-panel">
                                                <div class="accordion-heading" role="tab" id="headingOne">
                                                <h3 class="card-title accordion-title">
                                                    <a class="accordion-msg" data-toggle="collapse"
                                                       data-parent="#accordion" href="#collapseOne"
                                                       aria-expanded="true" aria-controls="collapseOne">
                                                        View
                                                    </a>
                                                </h3>
                                                </div>
                                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                                    <div class="accordion-content accordion-desc">
                                                        <p>
                                                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has
                                                            survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset
                                                            sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                   </div>-->
                                   <div class="col-md-3 col-lg-3 p-t-10">
                                        <div class="card visitor-card">
                                            <s:url action="pdb_listing.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>PDB Versions</span></br>
                                                        <span class="count"><s:property value="dashboard_result.vehicleversion_count"/></span>
                                                        <i class="icofont  icofont-car-alt-1 text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>
                                   
<!--                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="vehicle_listing.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>Vehicles</span>
                                                        <span class="count"><s:property value="dashboard_result.vehiclecount"/></span>
                                                        <i class="icofont  icofont-car-alt-2 text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>
                                            </a>    
                                        </div>
                                    </div>
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">  
                                            <s:url action="vehiclemodel_listing.action" var="aURL" />
                                            <s:a href="%{aURL}">     
                                                    <div class="card-block">
                                                        <span>Models</span>
                                                        <span class="count"><s:property value="dashboard_result.modelcount"/></span>
                                                        <i class="icofont icofont-racings-wheel text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>-->
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="features_listing.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>Domain & features</span></br>
                                                        <span class="count"><s:property value="dashboard_result.modelversion_count"/></span> 
                                                        <i class="icofont  icofont-numbered text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="create_pdb.action" var="aURL" />
                                            <s:a href="%{aURL}">      
                                                <div class="card-block">
                                                    <span>Create_PDB</span>
                                                    <span class="count"> + </span>
                                                    <i class="icofont icofont-plus-square text-c-red"></i>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </s:a>    
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                            
<%@include file="footer.jsp" %>
<script>
//        var app = angular.module('angularTable', []);

        app.controller('MyCtrl',function($scope, $http)
        {      
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
            $scope.task = [
                            {
                                type:"pdb"
                                name: "pdb version 1.0",
                                created_date: "2019-03-12 10:03:03",
                                created_by: "Anand",
                                acceptance_status: "Yes",
                                accepted_date: "2019-03-12 10:03:03",
                                accepted_by: "Anand",
                                completion_status: "Yes",
                                completion_date: "2019-03-12 10:03:03",
                            },
                            {
                                type:"safety"
                                name: "safety version 1.0",
                                created_date: "2019-03-12 10:03:03",
                                created_by: "Anand",
                                acceptance_status: "Yes",
                                accepted_date: "2019-03-12 10:03:03",
                                accepted_by: "Anand",
                                completion_status: "Yes",
                                completion_date: "2019-03-12 10:03:03",
                            },
                            {
                                type:"legislation"
                                name: "legislation version 1.0",
                                created_date: "2019-03-12 10:03:03",
                                created_by: "Anand",
                                acceptance_status: "Yes",
                                accepted_date: "2019-03-12 10:03:03",
                                accepted_by: "Anand",
                                completion_status: "Yes",
                                completion_date: "2019-03-12 10:03:03",
                            },
                            {
                                type:"ivn"
                                name: "ivn version 1.0",
                                created_date: "2019-03-12 10:03:03",
                                created_by: "Anand",
                                acceptance_status: "Yes",
                                accepted_date: "2019-03-12 10:03:03",
                                accepted_by: "Anand",
                                completion_status: "Yes",
                                completion_date: "2019-03-12 10:03:03",
                            },
                            {
                                type:"acb"
                                name: "acb version 1.0",
                                created_date: "2019-03-12 10:03:03",
                                created_by: "Anand",
                                acceptance_status: "Yes",
                                accepted_date: "2019-03-12 10:03:03",
                                accepted_by: "Anand",
                                completion_status: "Yes",
                                completion_date: "2019-03-12 10:03:03",
                            }
                        ];
        });
    </script> 
     
</body>

</html>