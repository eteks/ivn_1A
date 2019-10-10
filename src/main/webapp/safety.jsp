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
                                    <h4>Safety</h4>
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
                                    <li class="breadcrumb-item"><a href="#!">Safety</a> </li>
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
                                            <div class="accordion-panel" ng-if="task.legislation.completion_status == true">
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
                                                        <p><span>Name</span>: {{task.legislation.name}} </p>
                                                        <p><span>Created Date</span>: {{task.legislation.completion_date}} </p>
                                                        <p><span>Created By</span>: {{task.legislation.created_by}} </p>
                                                        <div ng-if="task.safety.acceptance_status == null" class="text-center">
                                                            <a href="#" ng-click="task_accept(task.legislation.task_id, task.legislation.tg_id)" class="btn-round btn btn-primary">Accept</a>&nbsp;&nbsp;
                                                            <a href="#" ng-click="task_reject(task.legislation.task_id, task.legislation.tg_id)" class="btn-round btn btn-danger">Reject</a>
                                                        </div>
                                                        <p><span>Status</span>: 
                                                            <label ng-if="task.safety.acceptance_status == true && task.safety.completion_status == null">Pending</label>
                                                            <label ng-if="task.safety.acceptance_status == false">rejected</label>
                                                            <label ng-if="task.safety.completion_status == true && task.safety.completion_status == true">Completed</label>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                           <hr></hr>
                                        </div>
                                   </div>
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="safety_combination.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>Safety Rule Builder</span></br>
                                                        <span class="count">{{vehicleversion_count}}</span>
                                                        <i class="icofont icofont-settings-alt text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>   
                                        </div>
                                    </div>
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">
                                            <s:url action="safety_list.action" var="aURL" />
                                            <s:a href="%{aURL}">   
                                                    <div class="card-block">
                                                        <span>Safety Version</span>
                                                        <span class="count">{{pdbversion_count}}</span>
                                                        <i class="icofont  icofont-safety-hat-light text-c-red"></i>  
                                                        <div class="clearfix"></div>
                                                    </div>
                                            </s:a>  
                                        </div>
                                    </div>
                                   
                                   <div class="col-md-3 col-lg-3">
                                        <div class="card visitor-card">  
                                            <s:url action="safety_ver_create.action" var="aURL" />
                                            <s:a href="%{aURL}">     
                                                    <div class="card-block">
                                                        <span>Add Safety version</span>
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
            $http({
                url: 'getTasks',
                method: "POST",
                data: {"froms":"Safetyversion"},
            }).then(function (response, status, headers, config){
                if(response.data.maps_string.success){
                    // $window.alert(JSON.stringify(response.data.maps_object));
                    // $window.alert(JSON.stringify(response.data.maps_object.tasks));
                    // console.log(JSON.stringify(response.data.maps_object.tasks))
                    // $scope.task = response.data.list_object;
                    $scope.task = response.data.maps_object.tasks;
                    $window.alert(JSON.stringify($scope.task));
                    //                            $window.document.getElementById('model').focus();
                } else {
                    $window.alert(JSON.stringify(response.data.maps_string));
                }
            });
               // $scope.task =
               //              {
               //                  pdb:{
               //                          name: "pdb version 1.0",
               //                          created_date: "2019-03-12 10:03:03",
               //                          created_by: "Anand",
               //                          acceptance_status: "yes",
               //                          accepted_date: "2019-03-12 10:03:03",
               //                          accepted_by: "Anand",
               //                          completion_status: "yes",
               //                          completion_date: "2019-03-12 10:03:03"
               //                      },
               //                      safety:
               //                      {
               //                          name: "safety version 1.0",
               //                          created_date: "2019-03-12 10:03:03",
               //                          created_by: "Anand",
               //                          acceptance_status: "",
               //                          accepted_date: "2019-03-12 10:03:03",
               //                          accepted_by: "Anand",
               //                          completion_status: "",
               //                          completion_date: "2019-03-12 10:03:03"
               //                      },
               //                      legislation:
               //                      {
               //                          name: "legislation version 1.0",
               //                          created_date: "2019-03-12 10:03:03",
               //                          created_by: "Anand",
               //                          acceptance_status: "yes",
               //                          accepted_date: "2019-03-12 10:03:03",
               //                          accepted_by: "Anand",
               //                          completion_status: "",
               //                          completion_date: "2019-03-12 10:03:03"
               //                      },
               //                      feature:
               //                      {
               //                          name: "legislation version 1.0",
               //                          created_date: "2019-03-12 10:03:03",
               //                          created_by: "Anand",
               //                          acceptance_status: "yes",
               //                          accepted_date: "2019-03-12 10:03:03",
               //                          accepted_by: "Anand",
               //                          completion_status: "",
               //                          completion_date: "2019-03-12 10:03:03"
               //                      },
               //                      ivn:
               //                      {
               //                          name: "ivn version 1.0",
               //                          created_date: "2019-03-12 10:03:03",
               //                          created_by: "Anand",
               //                          acceptance_status: "yes",
               //                          accepted_date: "2019-03-12 10:03:03",
               //                          accepted_by: "Anand",
               //                          completion_status: "",
               //                          completion_date: "2019-03-12 10:03:03"
               //                      },
               //                      acb:
               //                      {
               //                          name: "ivn version 1.0",
               //                          created_date: "2019-03-12 10:03:03",
               //                          created_by: "Anand",
               //                          acceptance_status: "yes",
               //                          accepted_date: "2019-03-12 10:03:03",
               //                          accepted_by: "Anand",
               //                          completion_status: "",
               //                          completion_date: "2019-03-12 10:03:03"
               //                      }
               //              };
                            $scope.task_accept = function(t_id, tg_id)
                            {
                                alert(tg_id);
                                var data = {};
                                data['t_id'] = t_id;
                                data['tg_id'] = tg_id;
                                data['username'] = '${sessionScope.user.username}';
                                data['uid'] = '${sessionScope.user.id}';
                                data['froms'] = 'Safetyversion';
                                data['stt'] = 'accept';
                                alert(JSON.stringify(data));
                                $http({
                                    url: 'createFirstLevelTask',
                                    method: "POST",
                                    data: data,
                                }).then(function (response, status, headers, config){

                                    if(response.data.maps_object.success){
                                        $window.alert(response.data.maps_object.success);
                                        var vals = JSON.parse(response.data.maps_object.vals.replace(/&quot;/g,'"'));
                                        $window.open("safety_ver_create.action?t_id="+vals.task_id.id+"&tg_id="+vals.id, "_self");
                                    } else {
                                        var stt = response.data.maps_object.failed ? response.data.maps_object.failed : response.data.maps_object.error;
                                        $window.alert(stt);
                                    }
                                });
                            };
                            $scope.task_reject = function(t_id, tg_id)
                            {
                                alert(tg_id);
                                var data = {};
                                data['t_id'] = t_id;
                                data['tg_id'] = tg_id;
                                data['username'] = '${sessionScope.user.username}';
                                data['uid'] = '${sessionScope.user.id}';
                                data['froms'] = 'Safetyversion';
                                data['stt'] = 'reject';
                                alert(JSON.stringify(data));
                                $http({
                                    url: 'createFirstLevelTask',
                                    method: "POST",
                                    data: data,
                                }).then(function (response, status, headers, config){

                                    if(response.data.maps_object.success){
                                        $window.alert(response.data.maps_object.success);
                                        var vals = JSON.parse(response.data.maps_object.vals.replace(/&quot;/g,'"'));
                                        $window.open("safety_ver_create.action?t_id="+vals.task_id.id+"&tg_id="+vals.id, "_self");
                                    } else {
                                        var stt = response.data.maps_object.failed ? response.data.maps_object.failed : response.data.maps_object.error;
                                        $window.alert(stt);
                                    }
                                });
                            };
 
                        
        });
    </script> 
     
</body>

</html>