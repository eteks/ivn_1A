<%@include file="header.jsp" %>
<%@include file="sidebar.jsp" %>

                    <div class="pcoded-content" ng-controller="RecordCtrl1 as Demo">
                        <div class="pcoded-inner-content">
                            <div class="main-body">

                                <div class="page-wrapper">

                                    <div class="page-header card">
                                        <div class="row align-items-end">
                                            <div class="col-lg-8">
                                                <div class="page-header-title">
                                                    <i class="icofont  icofont-mining bg-c-red"></i>
                                                    <div class="d-inline">
                                                        <h4>Safety</h4>
                                                        <span>Safety Listing</span>
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
                                                            <s:url action="safety.action" var="aURL" />
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
                                                        <div class="ng-table-scrollcontainer">
                                                        <table st-table="rowCollection" class="table table-striped">
                                                                <thead>
                                                                <tr>
                                                                    
                                                                    <th ng-click="sort('version')" class="text-center  ng-table-fixedcolumn">No</th>
                                                                    <th ng-click="sort('version')" class="">Safety Version</th>
                                                                    <th ng-click="sort('vehicle')" class="">Vehicle Details</th>
                                                                    <th ng-click="sort('vehicle')" class="">PDB Details</th>
                                                                    <th ng-click="sort('status')" class="text-center">Status</th>
                                                                    <th ng-click="sort('status')" class="text-center">Version Type</th>
                                                                    <th ng-click="sort('vehicle')" class="">Created Date</th>
                                                                    <th ng-click="sort('vehicle')" class="">Modified Date</th>
                                                                    <th ng-click="sort('action')" class="text-center">Action</th>
                                                                    
                                                                </tr>
                                                                </thead>
                                                                <tbody ng-init="getAllDomain_and_Features()">
                                                                    
                                                                    <tr dir-paginate="record in safety|orderBy:sortKey:reverse|filter:search|itemsPerPage:5">
                                                                        
                                                                       <td class="text-center  ng-table-fixedcolumn">
                                                                           
                                                                                {{$index+1}}
                                                                                
                                                                        </td>
                                                                        <td class="">
                                                                           
                                                                                {{record.saf}}
                                                                                
                                                                        </td>
                                                                        <td class="text-left">
                                                                           <a class="mytooltip p-l-10 p-r-10 blink" href="javascript:void(0)"> 
                                                                                {{record.vehicle}}
                                                                                <span class="tooltip-content5">
                                                                                    <span class="tooltip-text3">
                                                                                        <span class="tooltip-inner2">
                                                                                            <h4>{{record.version}}</h4>
                                                                                            <ul class="model-list">
                                                                                                <li ng-repeat="mod in (record.model | customSplitString)"><i class="icofont icofont-hand-right"></i> {{mod}}</li>
                                                                                            </ul>
                                                                                        </span>
                                                                                    </span>
                                                                                </span>
                                                                            </a>
                                                                        </td>
                                                                        
                                                                        <td class="">
                                                                           
                                                                                {{record.version}}
                                                                                
                                                                        </td>
                                                                        <td class="text-center"> 
                                                                            <button class="btn btn-default btn-bg-c-blue btn-outline-default btn-round btn-action" ng-if="record.status === true">Active
                                                                            </button>
                                                                            <button class="btn btn-default btn-bg-c-yellow btn-outline-default btn-round btn-action" ng-if="record.status === false">Inactive
                                                                            </button>
                                                                        </td>                                                                        
                                                                        <td class="text-center">                                                                             
                                                                            <span ng-if="record.flag === false">Temporary</span>
                                                                            <span ng-if="record.flag === true">Permanent</span>
                                                                        </td>
                                                                        <td class="">
                                                                           
                                                                                {{record.created_date}}
                                                                                
                                                                        </td>
                                                                        <td class="">
                                                                           
                                                                                {{record.modified_date}}
                                                                                
                                                                        </td>
                                                                        <td class="text-center"> 

                                                                            <button class="btn btn-default btn-bg-c-blue btn-outline-primary btn-round" data-id="{{record.saf_id}}" ng-click="View_and_edit($event)" name="edit" ng-if="record.flag === false || record.status === false">Edit</button>

                                                                            <button class="btn btn-default btn-bg-c-blue btn-outline-danger btn-round" data-id="{{record.saf_id}}" ng-click="View_and_edit($event)" name="view" ng-if="record.status === true && record.flag === true">view</button>

                                                                            <button class="btn btn-default btn-bg-c-blue btn-outline-primary btn-round" data-id="{{record.saf_id}}" ng-click="delete($event)" name="delete" ng-if="record.delBut === 1">Delete</button>
                                                                        </td>
<!--                                                                        <td class="text-center">
                                                                           
                                                                                <a href="#" ng-click="removeRow(record.fid)"><i class="icofont icofont-ui-close text-c-red"></i></a>
                                                                                
                                                                        </td>-->
                                                                        
                                                                    </tr>

                                                                </tbody>
                                                            </table>
                                                        </div>
                                                            <dir-pagination-controls
                                                                max-size="5"
                                                                direction-links="true"
                                                                boundary-links="true" >
                                                            </dir-pagination-controls>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                            
                 
                                           
<%@include file="footer.jsp" %>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>-->
  <!--<script src="js/dirPagination.js"></script>-->
    <script>
//        var app = angular.module('angularTable', ['angularUtils.directives.dirPagination']);

        app.controller('RecordCtrl1',function($scope, $http, $window, $location, $element, $rootScope)
        {

            this.data = [];
            if ("<s:property value="result_data_obj"/>") {
                
                $scope.safety = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
                alert(JSON.stringify($scope.safety));
            } else {
                alert("Data Loading Error");
            }
//               alert(JSON.stringify($scope.safety));
//              $scope.safety = [{"flag":true,"saf_id":1,"model":"a1,a2,a3","created_date":"Sep 19, 2019 4:27:38 PM","modified_date":"Sep 19, 2019 4:27:38 PM","version":"1.0","saf":"1.0","vehicle":"audi","status":true}]; 
          
            $scope.View_and_edit = function(event){
//                alert("view_and_edit");
//                alert(event.target.id);
                var id = event.target.attributes['data-id'].value;
//                $window.alert(id.toString());
                var name = event.target.name;
//                alert(id+" "+name+" ");
//                $http.get("vehicle_add.action", {
//                    params: { "id": id }
//                });
                $window.open("safety_ver_create.action?id="+id+"&action="+name,"_self");
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
                     if(data.data.dlStatus.status === 1){
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
                if(input !=undefined){
                    var arr = input.split(',');
                    return arr;
                }                
            };     
        });

    $(document).ready(function(){
        // initialize modal
        $('.modal-trigger').leanModal();
    });
    
    </script>   
</body>

</html>                                            