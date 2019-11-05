<%@include file="header.jsp" %>
<%@include file="sidebar.jsp" %>

                    <div class="pcoded-content" ng-controller="RecordCtrl1">
                        <div class="pcoded-inner-content">
                            <div class="main-body">

                                <div class="page-wrapper">

                                    <div class="page-header card">
                                        <div class="row align-items-end">
                                            <div class="col-lg-8">
                                                <div class="page-header-title">
                                                    <i class="icofont  icofont-mining bg-c-red"></i>
                                                    <div class="d-inline">
                                                        <h4>Feature Version</h4>
                                                        <span>Listing</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="page-header-breadcrumb">
                                                    <ul class="breadcrumb-title">
                                                        <li class="breadcrumb-item">
                                                            <a href="index.html"> <i class="icofont icofont-home"></i> </a>
                                                        </li>
                                                        <li class="breadcrumb-item">
                                                            <s:url action="feature.action" var="aURL" />
                                                            <s:a href="%{aURL}">
                                                                Back
                                                            </s:a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="page-body">
                                        <div class="row">

                                            <!-- Marketing Start -->
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="card-block marketing-card p-t-0">
                                                        <form class="form-inline mt-3">
                                                            <div class="form-group">
                                                                <input type="text" ng-model="search" class="form-control" placeholder="Search">
                                                            </div>
                                                        </form>
<!--                                                        <a class="feature_add sig_add modal-trigger" href="#comparelist">
                                                            Compare
                                                        </a>-->
                                                        <div class="ng-table-scrollcontainer">
                                                            <table st-table="rowCollection" class="table table-striped">
                                                                    <thead>
                                                                    <tr>
                                                                        <th ng-click="sort('veh_version')" class="text-center  ng-table-fixedcolumn">Combination</th>
                                                                        <th ng-click="sort('pdb_version')" class="text-center">Feature Version</th>                                                                         
                                                                        <th ng-click="sort('pdb_version')" class="text-center">Vehicle</th>                                                                         
                                                                        <th ng-click="sort('status')" class="text-center">Status</th>
                                                                        <th ng-click="sort('status')" class="text-center">Version Type</th>
                                                                        <th ng-click="sort('created_date')" class="text-center">Created Date</th>
                                                                        <th ng-click="sort('modified_date')" class="text-center">Modified Date</th>
                                                                        <th ng-click="sort('action')" class="text-center">Action</th>

                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>

                                                                        <tr dir-paginate="record in records|orderBy:sortKey:reverse|filter:search|itemsPerPage:5">
                                                                             
                                                                            <td class="text-center ng-table-fixedcolumn">
                                                                               <a class="mytooltip p-l-10 p-r-10 blink" href="javascript:void(0)"> 
                                                                                    <i class="icofont icofont-hand-drawn-up"></i>
                                                                                    <span class="tooltip-content5">
                                                                                        <span class="tooltip-text3">
                                                                                            <span class="tooltip-inner2">
                                                                                                <h3>Version</h3>
                                                                                                <ul class="model-list">
                                                                                                    
                                                                                                    <!--<li ng-repeat="mod in (record.combine | customSplitString)"><i class="icofont icofont-hand-right"></i> {{mod}}</li>-->
                                                                                                    <li><i class="icofont icofont-hand-right"></i> PDB_{{record.pdb_versionname}}</li>
                                                                                                    <li><i class="icofont icofont-hand-right"></i> LEG_{{record.legislation_versionname}}</li>
                                                                                                    <li><i class="icofont icofont-hand-right"></i> SAF_{{record.safety_versionname}}</li>
                                                                                                </ul>
                                                                                            </span>
                                                                                        </span>
                                                                                    </span>
                                                                                </a>     
                                                                            </td>
                                                                            <td class="text-center">                                                                           
                                                                                    {{record.feature_versionname}}                                                                                
                                                                            </td>
                                                                            <td class="text-center ">                                                                           
                                                                                    {{record.vehiclename}}                                                                                
                                                                            </td>                                                                            
                                                                            <td class="text-center"> 

                                                                                <button class="btn btn-default btn-bg-c-blue btn-outline-default btn-round btn-action" ng-if="record.status == true">Active
                                                                                </button>

                                                                                <button class="btn btn-default btn-bg-c-yellow btn-outline-default btn-round btn-action" ng-if="record.status == false">Inactive
                                                                                </button>

                                                                            </td>
                                                                            <td class="text-center">                                                                             
                                                                                <span ng-if="record.flag == false">Temporary</span>
                                                                                <span ng-if="record.flag == true">Permanent</span>
                                                                            </td>
                                                                            <td class="text-center">{{record.created_date}}</td>
                                                                            <td class="text-center">{{record.modified_date}}</td>
                                                                            <td class="text-center"> 

                                                                                <button class="btn btn-default btn-bg-c-blue btn-outline-primary btn-round" data-id="{{record.id}}" ng-click="View_and_edit($event)" name="edit" ng-if="record.flag == false || record.status == false">Edit</button>

                                                                                <button class="btn btn-default btn-bg-c-blue btn-outline-danger btn-round" data-id="{{record.id}}" ng-click="View_and_edit($event)" name="view" ng-if="record.status == true && record.flag == true">view</button>

                                                                                <button class="btn btn-default btn-bg-c-blue btn-outline-primary btn-round" data-id="{{record.id}}" ng-click="delete($event)" name="delete" ng-if="record.delBut == 1">Delete</button>
                                                                            </td>
                                                                        </tr>

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        <!--table Scroller-->
                                                            <dir-pagination-controls
                                                                max-size="5"
                                                                direction-links="true"
                                                                boundary-links="true" >
                                                            </dir-pagination-controls>
                                                        
                                                    </div>
                                                </div>
                                            </div>
<!--            <div id="comparelist" class="modal modal-feature-list compare_mod">
                <div class="modal-content">
                    <h5 class="text-c-red m-b-10">Compare <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                    <div class="comp_search">
                        <select class="js-example-basic-single col-sm-12 form-control form-control-primary"  ng-model="countSelected" ng-change="comp_1(countSelected)" ng-options="category as category.versionname for category in compare_records">
                            <option value="">Select Version</option>
                            <optgroup label="Designer">
                                <option value="WY">Peter</option>
                                <option value="WY">Hanry Die</option>
                                <option value="WY">John Doe</option>
                            </optgroup>
                        </select>
                        <p class="m-t-10 text-center">
                            <strong>With</strong>
                        </p>
                        <select class="js-example-basic-single col-sm-12 form-control form-control-primary"  ng-model="countSelected" ng-change="comp_2(countSelected)" ng-options="category as category.versionname for category in compare_records">
                            <option value="">Select Version</option>
                            <optgroup label="Designer">
                                <option value="WY">Peter</option>
                                <option value="WY">Hanry Die</option>
                                <option value="WY">John Doe</option>
                            </optgroup>
                        </select>
                    </div>
                    <div class="comp_slot_1" ng-repeat="record in compare_1">
                        <div><label>Version</label>:{{record.versionname}}</div>
                        <div ng-repeat="r in record.vehicledetails">
                            <div><label>Vehicle Name</label>:{{r.vehiclename}}</div>
                            <div><label>Vehicle Version</label>:{{r.vehicleversion}}</div>
                            <div><label>Models Name</label>:{{r.modelname}}</div>
                        </div>
                    </div>
                    <div class="comp_slot_2" ng-repeat="record in compare_2">       
                        <div><label>Version</label>:{{record.versionname}}</div>
                        <div ng-repeat="r in record.vehicledetails">
                            <div><label>Vehicle Name</label>:{{r.vehiclename}}</div>
                            <div><label>Vehicle Version</label>:{{r.vehicleversion}}</div>
                            <div><label>Models Name</label>:{{r.modelname}}</div>
                        </div>
                    </div>
                </div>
            </div>-->
                                            <!-- Marketing End -->
                                            
<%@include file="footer.jsp" %>

  <!--<script src="js/dirPagination.js"></script>-->
    <script>
//        var app = angular.module('angularTable', ['angularUtils.directives.dirPagination']);

        app.controller('RecordCtrl1',function($scope, $http, $window)
        {
//             $scope.records = [
//                        { feature_version: '1.0', vehicle: 'Scorpio',combine:'PDB_1.0,safety_1.0,legislation_1.0', status: 'Active'},
//                        { feature_version: '2.0', vehicle: 'Xuv',combine:'PDB_2.0,safety_2.0,legislation_2.0', status: 'Inactive'},
//                        { feature_version: '3.0', vehicle: 'Scorpio',combine:'PDB_3.0,safety_3.0,legislation_3.0', status: 'Active'}
//                    ];
            
            if ("<s:property value="result_data_obj"/>") {
                
                $scope.records = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
                alert(JSON.stringify($scope.records));
            } else {
                alert("Data Loading Error");
            }

            $scope.View_and_edit = function(event){
                var id = event.target.attributes['data-id'].value;
                var name = event.target.name;
//                alert(id+"  "+name);
                $window.open("feature_version_create.action?id="+id+"&action="+name,"_self"); //
            }

            $scope.delete = function(event){
//                alert("view_and_edit");
//                alert(event.target.id);
                var id = event.target.attributes['data-id'].value;
                var data = {id : id};
                $http({
                    url : 'deletepdbversion',
                    method : "POST",
                    data : data
                })
                    .then(function (data, status, headers, config){
                        if(data.data.dlStatus.status == 1){
                            alert("PDB Version Deleted Succesfully");
                            $window.location.reload();
                        }else{
                            alert("Error while deleting PDB Version");
                        }
                    });
            }
        });
        app.filter('customSplitString', function() 
        {
            return function(input) 
            {
                if(input){
                    var arr = input.split(',');
                    return arr;
                }                
            };     
        });
    </script>   
</body>

</html>                                            