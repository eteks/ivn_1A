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
                                    <h4>IVN Engineer</h4>
                                    <span>Vehicle, CAN, LIN, H/W  and IVN version</span>                                 
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="page-header-breadcrumb">
                                <ul class="breadcrumb-title">
                                    <li class="breadcrumb-item">
                                        <a href="index.html"> <i class="icofont icofont-home"></i> </a>
                                    </li>
                                    <li class="breadcrumb-item"><a href="#!">IVN</a> </li>
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
                                            <h5 class="card-header-text">Task</h5>
                                        </div>
                                       
                                       <div id="accordion" role="tablist" aria-multiselectable="true">
                                            <div class="accordion-panel" ng-if="task.feature.completion_status == 'yes'">
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
                                                        <p><span>Name</span>: {{task.feature.name}} </p>
                                                        <p><span>Created Date</span>: {{task.feature.created_date}} </p>
                                                        <p><span>Created By</span>: {{task.feature.created_by}} </p>
                                                        <div ng-if="task.ivn.acceptance_status == ''" class="text-center">
                                                            <a href="#" ng-click="task_accept()" class="btn-round btn btn-primary">Accept</a>&nbsp;&nbsp;
                                                            <a href="#" ng-click="task_reject()" class="btn-round btn btn-danger">Reject</a>
                                                        </div>
                                                        <p><span>Status</span>: 
                                                            <label ng-if="task.ivn.acceptance_status == 'yes'">Pending</label>
                                                            <label ng-if="task.ivn.acceptance_status == 'no'">rejected</label>
                                                            <label ng-if="task.ivn.completion_status == 'yes'">Completed</label>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                           <hr></hr>
                                        </div>
                                   </div>
<!--                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="vehicleversion_listing.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>Vehicle version</span></br>
                                                        <span class="count"><s:property value="dashboard_result.vehicleversion_count"/></span>
                                                        <span class="count">#</span>
                                                        <i class="icofont icofont-car-alt-1 text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>-->
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="ivn_version_listing.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>IVN Version</span></br>
                                                        <span class="count"><s:property value="dashboard_result.ivnversion_count"/></span>      
                                                        <i class="icofont icofont-chart-flow-alt-1 text-c-red"></i> 
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="hw_ecu_listing.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                <div class="card-block">
                                                    <span>Network</span>
                                                    <span class="count"><s:property value="dashboard_result.network_count"/></span>  
                                                    <i class="icofont icofont-chart-flow text-c-red"></i>
                                                    
                                                    <div class="clearfix"></div>
                                                </div>
                                            </s:a>                                             
                                        </div>
                                    </div>
                                   
<!--                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">  
                                            <s:url action="ivn_signals.action" var="aURL" />
                                            <s:a href="%{aURL}">     
                                                    <div class="card-block">
                                                        <span>Signals</span>
                                                        <span class="count"><s:property value="dashboard_result.signal_count"/></span>
                                                        <i class="icofont  icofont-connection text-c-red"></i> 
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>-->
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="ivn_version_create.action" var="aURL" />
                                            <s:a href="%{aURL}">      
                                                <div class="card-block">
                                                    <span>IVN Version Create</span>
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
        app.controller('MyCtrl',function($scope, $http, $window)
        {      
//            var data = JSON.parse("<s:property value="count"/>".replace(/&quot;/g,'"'));
////            alert(JSON.stringify(data));
//            $scope.count ={"ivnversion_count":data['ivnversion_count'],
//                           "ecucount":data['ecucount']
//                          } ;
$scope.task = 
                            {
                                pdb:{
                                        name: "pdb version 1.0",
                                        created_date: "2019-03-12 10:03:03",
                                        created_by: "Anand",
                                        acceptance_status: "yes",
                                        accepted_date: "2019-03-12 10:03:03",
                                        accepted_by: "Anand",
                                        completion_status: "yes",
                                        completion_date: "2019-03-12 10:03:03"
                                    },
                                    safety:
                                    {
                                        name: "safety version 1.0",
                                        created_date: "2019-03-12 10:03:03",
                                        created_by: "Anand",
                                        acceptance_status: "yes",
                                        accepted_date: "2019-03-12 10:03:03",
                                        accepted_by: "Anand",
                                        completion_status: "yes",
                                        completion_date: "2019-03-12 10:03:03"
                                    },
                                    legislation:
                                    {
                                        name: "legislation version 1.0",
                                        created_date: "2019-03-12 10:03:03",
                                        created_by: "Anand",
                                        acceptance_status: "yes",
                                        accepted_date: "2019-03-12 10:03:03",
                                        accepted_by: "Anand",
                                        completion_status: "yes",
                                        completion_date: "2019-03-12 10:03:03"
                                    },
                                    feature:
                                    {
                                        name: "legislation version 1.0",
                                        created_date: "2019-03-12 10:03:03",
                                        created_by: "Anand",
                                        acceptance_status: "yes",
                                        accepted_date: "2019-03-12 10:03:03",
                                        accepted_by: "Anand",
                                        completion_status: "yes",
                                        completion_date: "2019-03-12 10:03:03"
                                    },
                                    ivn:
                                    {
                                        name: "ivn version 1.0",
                                        created_date: "2019-03-12 10:03:03",
                                        created_by: "Anand",
                                        acceptance_status: "",
                                        accepted_date: "2019-03-12 10:03:03",
                                        accepted_by: "Anand",
                                        completion_status: "",
                                        completion_date: "2019-03-12 10:03:03"
                                    },
                                    acb:
                                    {
                                        name: "ivn version 1.0",
                                        created_date: "2019-03-12 10:03:03",
                                        created_by: "Anand",
                                        acceptance_status: "",
                                        accepted_date: "2019-03-12 10:03:03",
                                        accepted_by: "Anand",
                                        completion_status: "",
                                        completion_date: "2019-03-12 10:03:03"
                                    }
                            };
                            $scope.task_accept = function()
                            {
                                $scope.task.ivn.acceptance_status="yes";
                                $window.open("ivn_version_create.action","_self");
                            }
                            $scope.task_reject = function()
                            {
                                $scope.task.ivn.acceptance_status="no";
                            }
 
        });
</script> 
     
</body>

</html>