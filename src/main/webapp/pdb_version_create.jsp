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
                                                    <i class="icofont icofont-mining bg-c-red"></i>
                                                    <div class="d-inline">
                                                        <h4>PDB Owner</h4>
                                                        <span>PDB Version Creation</span>
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
                                                            <s:url action="pdb_owner.action" var="aURL" />
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
                        <div class="col-lg-12">
                            <form class=""  name="myForm">
                            <div ng-tabs class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li ng-tab-head ="active">
                                        <a href="#" ng-click="tabstep1()">Vehicle</a>
                                    </li>
                                    <li ng-tab-head>
                                        <a href="#" ng-click="tabstep2()">PDB</a>
                                    </li>
                                </ul>
                                
                                <div class="tab-content m-t-10" style="overflow: hidden">
                                    
                                    <div ng-tab-body="animated " class="tab-pane">
                                        <div class="col-md-6 float-left">
                                                <div class="card">
                                                   <div class="card-block marketing-card p-t-20">
                                                        
                                                                <div class="form-group text-right">

                                                                </div>                
                                                                <div>
                                                                    <div ng-if="Demo.data">
                                                                        <div class="form-group">
                                                                            <label for="vehicle">Select vehicle:</label>
                                                                            <input type="radio" ng-model="new_vehicle" value="select_vehicle"/>
                                                                        </div>
                                                                        <div class="form-group"  ng-if="new_vehicle=='select_vehicle'">
                                                                            <label for="vehiclename">Vehicle:</label>
                                                                            <select id="vehiclename" ng-model="data.vehicle" ng-change="LoadPreviousVersion()">
                                                                                <option selected="" disabled="">Select Version</option>
                                                                                <s:iterator value="vehicleversion_result" var="data" >
                                                                                    <option value="<s:property value="id"/>"><s:property value="vehiclename"/></option>
                                                                                </s:iterator>
                                                                            </select>
                                                                            <label for="vehicle">Version:</label>
                                                                            <select ng-model="data.pdbversion" ng-options="y.pdbversion for (x, y) in array_result | orderBy:'-'" ng-change="LoadVehicleModels()" ></select>
                                                                            <!--<select ng-init="arr[arr.length-1]" ng-model="data.pdbversion" ng-options="y.pdbversion for (x, y) in array_result track by arr | orderBy:'-'" ng-change="LoadVehicleModels()" ></select>-->
<!--                                                                            <select ng-change="LoadVehicleModels()" ng-model="data.pdbversion" id="pdbversion">
                                                                                <option ng-repeat="arr in array_result | orderBy:'-'" ng-selected="arr.pdbid == data.pdbversion" value="{{arr.pdbid}}" >{{arr.pdbversion}}</option>
                                                                            </select>-->
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="vehicle">New vehicle:</label>
                                                                            <input type="radio" ng-model="new_vehicle" value="new_vehicle"/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="vehicle">Vehicle:</label>
                                                                            <input type="text" class="form-control" placeholder="Enter vehicle" name="vehicle"  ng-model="Demo.dt.vehiclename" required>
                                                                            <span ng-show="myForm.vehicle.$touched && myForm.vehicle.$invalid">The name is required.</span>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="model">Model:</label>
                                                                            <tags-input ng-model="Demo.dt.modelname.split(',')" use-strings="true"></tags-input>
                                                                        </div>                                                      
                                                                    </div>                                                    
                                                                </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 float-left">
                                                <div class="card">
                                                    <div class="row card-block marketing-card">
                                                        <div class="col-md-12 mod_vec_animate" ng-if="Demo.dt">
                                                            <h5 class="m-t-20"><i class="icofont icofont-steering"></i> {{Demo.dt.vehiclename}}</h5>
                                                            <ul>
                                                                <li ng-repeat="item in Demo.dt.modelname.split(',')" >
                                                                    <i class="icofont icofont-whisle text-c-red"></i> {{item}} 
                                                                </li>
                                                            </ul>
                                                        </div> 
                                                    </div>
                                                </div>
                                            </div>
                                    </div><!--tab-body-->
                                    <div ng-tab-body="animated " class="tab-pane card">
                                            <div class="card-block marketing-card p-t-0">
                                                <div class="row p-t-30">
                                                   
                                                   <a class=" modal-trigger" href="#modal-feature-list">
                                                       <i class="icofont icofont-ship-wheel text-c-red"></i>
                                                       Feature List
                                                   </a>&nbsp&nbsp
                                                   <a class="feature_add_tip waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-product-form" ng-click="showCreateForm()">Add Feature</a>

                                               </div>   
                                           <div class="ng-table-scrollcontainer" style="margin-left:300px;">        
                                               <table st-table="rowCollection" class="table table-striped">
                                                       <thead>
                                                       <tr>

                                                           <th class="ng-table-fixedcolumn">Domain</th>
                                                           <th class="ng-table-fixedcolumn" style="left:150px;">Features</th>
                                                           <th class="text-center" ng-repeat="i in records">
                                                               {{i.modelname}}
                                                           </th>

                                                       </tr>
                                                       </thead>

                                                       <tbody>
                                                           
                                                           <tr dir-paginate="record in features|orderBy:sortKey:reverse|filter:search|itemsPerPage:20">

                                                              <td class="ng-table-fixedcolumn">
                                                                  <span class="compresslength" style="display:block">{{record.domain}}</span>                                                                           
                                                               </td>
                                                               <td class="ng-table-fixedcolumn" style="left:150px;">
                                                                   <a href="#" class="float-left" ng-click="removeRow(record.fid)"><i class="icofont icofont-ui-close text-c-red"></i></a> <span class="compresslength" style="display:block;padding-left:10px;">{{record.fea}}</span>
                                                               </td>
                                                               <td class="text-center" ng-repeat="i in records">                                                                             
                                                                     <label class="custom_radio mytooltip tooltip-effect-8">                                                                                
                                                                       <input type="radio" ng-click="radiovalue(record.fid,i.model_id,'y')" name="f{{record.fid}}_{{i.model_id}}" value="y" class="radio_button">
                                                                       <span class="checkmark c_b_g">                                                                                    
                                                                       </span>
                                                                       <span class="tooltip-content2">yes</span>
                                                                     </label>
                                                                     <label class="custom_radio mytooltip tooltip-effect-8">
                                                                       <input type="radio" ng-click="radiovalue(record.fid,i.model_id,'n')" name="f{{record.fid}}_{{i.model_id}}" value="n" class="radio_button">
                                                                       <span class="checkmark c_b_r"></span>
                                                                       <span class="tooltip-content2">no</span>
                                                                     </label>
                                                                     <label class="custom_radio mytooltip tooltip-effect-8">
                                                                       <input type="radio" ng-click="radiovalue(record.fid,i.model_id,'o')" name="f{{record.fid}}_{{i.model_id}}" value="o" class="radio_button">    
                                                                       <span class="checkmark c_b_b"></span>
                                                                       <span class="tooltip-content2">optional</span>
                                                                     </label>
                                                               </td>
                                                           </tr>
                                                       
                                                       </tbody>
                                                   </table>
                                           </div>          
                                           <dir-pagination-controls
                                                   max-size="20"
                                                   direction-links="true"
                                                   boundary-links="true" >
                                           </dir-pagination-controls>                                                        
                                           </div>
                                           <div class="text-right p-10">
                                                <a class="modal-trigger float-left text-c-green" style="font-weight:600;padding-left: 10px;" href="#modal-upload" style="text-decoration:underline;" ng-click="assignstart(record.fid)">
                                                   Import
                                               </a>
                                               
                                               <label for="status" style="vertical-align:middle">Status:</label>
                                               <label class="switch m-r-50"  style="vertical-align:middle">
                                                   <input type="checkbox" ng-model="data.status">
                                                   <span class="slider round"></span>
                                                </label>

                                               
                                               <a class="feature_add_tip modal-trigger btn-floating btn-primary" style="padding:10px" href="#modal-comment" ng-click="showCreateForm()">Proceed</a>
                                               <div id="modal-comment" class="modal">
                                                    <div class="modal-content text-left">
                                                        <h5 class="text-c-red m-b-10">Comment <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                                                        <textarea class="col-md-12 m-b-10"></textarea>
                                                        <div class="text-right">
                                                            <button ng-show="showSave == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createpdbversion('save')" name="save">Save</button>
                                                            <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createpdbversion('submit')" name="submit">Submit</button>
                                                        </div>
                                                    </div>
                                                </div>
                                           </div>
                                    </div><!--tab-body-->
                                </div>
                            </div>
                            </form>

                       </div><!--col md 12-->
                       <div id="modal-upload" class="modal">
                        <div class="modal-content">
                            <h5 class="text-c-red m-b-10"><a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                <!--                        <div class="float-left">
                                <input type="file" name="userImport" label="User File" />
                                 <button  class="btn btn-primary">Import</button>
                            </div>-->
                            <div ng-controller = "fileCtrl" class="float-left">
                                <input type = "file" name="userImport" file-model = "myFile" accept=".csv"/>
                                <button class="btn btn-primary" ng-click = "uploadFile()">Import</button>
                            </div>
                        </div>
                        <div class="loader-block" style="display:none;">
                            <div class="preloader6">
                                <hr>
                            </div>
                        </div>
                    </div>
                       
                    <div id="modal-product-form" class="modal">
                    <div class="modal-content">
                        <h5 class="text-c-red m-b-25">Add Feature<a class="modal-action modal-close waves-effect waves-light float-right m-t-5"> <strong><em>Close</em></strong></a></h5>
                       
                            <div class="split1">
                                <input ng-model="domain" type="text" class="validate col-lg-12" id="form-name" placeholder="Domain"/>
                            </div>
                            <div class="split2" ng-repeat="data in Demo.data">         
                                <p class="text-right">
                                    <a href="" ng-click="Demo.data.splice($index,1)">
                                        <i class="icofont icofont-ui-close text-c-red "></i>
                                    </a>
                                 </p>
                                <div class="form-group">
                                    <input ng-model="data.feature" type="text" class="validate  col-lg-12" id="form-name" placeholder="Feature"/>
                                </div>
                                <div class="form-group">                                                                
                                    <input ng-model="data.type" type="radio" class="validate" placeholder="Electrical" value="electrical"/>
                                    <label for="name">Electrical</label>&nbsp<span>/</span>
                                    <input ng-model="data.type" type="radio" class="validate" placeholder="Non Electrical" value="non_electrical"/>
                                    <label for="name">Non Electrical</label>
                                </div>
                                <div class="form-group">
                                    <textarea ng-model="data.description" type="text" class="validate materialize-textarea  col-lg-12" placeholder="Description"></textarea>
                                </div>
                            </div>
                            <div style="clear:both"></div>
                            <p class="text-right">
                                <a href="" ng-click="Demo.data[Demo.data.length] = {}" class="text-c-green">
                                    <strong>Clone</strong>
                                 </a>
                            </p>
                            
                            <div class="input-field text-center">
                                <!--<a id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em float-right" ng-click="createfeature()">Add</a>-->
                                <button id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em btn-primary" ng-click="createfeature_and_domain()" ng-mousedown='doSubmit=true' name="add">Save</button>
                            </div>
                    </div>
                </div>
                <!-- floating button for creating product -->
            <!--</form>-->
            
            <div id="modal-feature-list" class="modal modal-feature-list">
                <div class="modal-content">
                    <h5 class="text-c-red m-b-10">Feature <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                    
                    <ul>
                        <li ng-repeat="fil in features_list">
                            <a href="#" class="text-c-green" ng-click="add_feature_tab(fil.fid)">
                                <i class="icofont icofont-ui-add"></i></a>&nbsp;({{fil.domain}})&nbsp;{{fil.fea}}
                        </li>
                    </ul>
                    
                </div>
            </div>
            
            
<!--            <pre>list={{list}}</pre>-->
<%@include file="footer.jsp" %>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>-->

  <!--<script src="js/dirPagination.js"></script>-->
    <script>
//        var app = angular.module('angularTable', ['angularUtils.directives.dirPagination']);
        app.controller('RecordCtrl1',function($scope, $http, $window, $location, $element, $rootScope)
        {
         
            this.data=[];
            var notification_to;
            $scope.features = [];
            $scope.list = [];
            $scope.vehicleresults = {};
            $scope.vercompare_results = {};
            
            
            
            $scope.features_list = $scope.features_list = [{"fid":"1","fea":"FRT MNL A/C ON","domain":"AIR CONDITIONER"},{"fid":"2","fea":"FRT AUTO A/C ON (DUAL ZONE)","domain":"AIR CONDITIONER"}];
//            alert($scope.features_list);
            $scope.tabstep1 = function() 
            {
    //            alert('hi');
            }
            $scope.tabstep2 = function() 
            {
//                alert('hi');
//                $scope.vehicleresults = {"vehicle_id" :"1", "models":[{"model_id":1,"modelname":"m1"},{"model_id":2,"modelname":"m2"}]};   
                $scope.records = $scope.vehicleresults.models;
            }
            $scope.showSave =true;
            $scope.showSubmit =true;
            $scope.$on('notifyValue', function (event, args) {
                notification_to = args;
                $scope.createpdbAjax("submit");
            });
            $scope.data = {};
            
            $rootScope.$on("CallLoadPDBPreviousVersion", function(event,data){
//                alert(data);
                $scope.LoadPDBPreviousVersion(data);
            });
            $scope.createpdbAjax = function (event){
                var status = $scope.data.status;
                if(status == undefined || status == false)
                    notification_to = undefined;
                var data = {};
                alert(JSON.stringify($scope.list));
                $scope.data.vehicle_id = $scope.vehicleresults.vehicle_id;
                if($scope.data.pdbversion != undefined) 
                    $scope.data.pdbversion_id = $scope.data.pdbversion;
                    $scope.data.pdbversion_name = $scope.data.pdbversion;
//                data['pdbversion'] = {"vehicle_id": "1", "pdb_manual_comment":"test", "status": true};
    //            data['pdbversion'] = {"vehicle_id": "1", "pdb_manual_comment":"test", "status": true,"pdbversion_id": "1", "pdbversion_name":"1.0"};
                data['pdbversion'] = $scope.data;
//                data['pdbdata_list'] = [{"model_id":"1","dfm_id":"1","status":"y"},{"model_id":"1","dfm_id":"2","status":"n"},{"model_id":"1","dfm_id":"3","status":"o"},{"model_id":"2","dfm_id":"1","status":"o"},{"model_id":"2","dfm_id":"2","status":"n"},{"model_id":"2","dfm_id":"3","status":"y"},{"model_id":"3","dfm_id":"1","status":"y"},{"model_id":"3","dfm_id":"2","status":"n"},{"model_id":"3","dfm_id":"3","status":"o"}];
                data['pdbdata_list'] = $scope.list;
                data['button_type'] = event;
                data['notification_to'] = notification_to+"";
                data['dfm_set'] = ["1","2","3"];
                alert(JSON.stringify(data));
                $http({
                    url: 'createpdbversion',
                    method: "POST",
                    data: data,
                }).then(function (data, status, headers, config) {
                    $scope.vercompare_results = {"removed_features":"(d1) feature3, (d1) feature5", 
                                                 "added_features":"(d1) feature4", 
                                                 "removed_models":"m2,m4", "added_models":"m3", 
                                                 "previous_version":"1.0", "current_version":"1.1"
                                                };
    //                                    $window.alert(JSON.stringify(data));
    //                                      alert(JSON.stringify(data.data.maps.status).slice(1, -1));
    //                                      $window.open("pdb_listing.action","_self"); //                alert(data.maps);
    //            //                        Materialize.toast(data['maps']["status"], 4000);
                });
            }
            
            $scope.createpdbversion = function (event)
            {
              alert("createpdbversion");
//            var status = true;
            var status = $scope.data.status;
            if(status == undefined )
                status = false;
//            if (!$scope.doSubmit) 
//            {
//                return;
//            }
//            $scope.doSubmit = false;  
            alert(JSON.stringify($scope.list));
//            alert($scope.list.length);
//            alert($scope.records.length * $scope.features.length);
            if($scope.list.length > 0){
                if($scope.list.length === $scope.records.length * $scope.features.length){
//                    if(status && event === "submit"){
//                        $(".notifyPopup").click();
//                    }else
//                        $scope.createpdbAjax(event);
                    $scope.createpdbAjax(event);
                }
                else
                    alert("Please fill all the domain and feature status to create PDB version");
            }
            else{
                alert("Please fill the domain and feature status to create PDB version");
            }        
          }
                             
            $scope.sort = function(keyname)
            {
                $scope.sortKey = keyname;   //set the sortKey to the param passed
                $scope.reverse = !$scope.reverse; //if true make it false and vice versa
            }
            $scope.add_feature_tab = function(fid)
            {				
		var index = -1;		
		var comArr = eval( $scope.features_list );
		for( var i = 0; i < comArr.length; i++ ) 
                {
                    if( comArr[i].fid === fid ) 
                    {
                        index = i;
                        break;
                    }
		}
		if( index === -1 ) 
                {
			alert( "Something gone wrong" );
		}
                $scope.features.push({fid:comArr[index].fid,domain:comArr[index].domain,fea: comArr[index].fea})
		$scope.features_list.splice( index, 1 );
                
            };
            $scope.removeRow = function(fid)
            {				
		var index = -1;		
		var comArr = eval( $scope.features );
		for( var i = 0; i < comArr.length; i++ ) 
                {
                    if( comArr[i].fid === fid ) 
                    {
                        index = i;
                        break;
                    }
		}
		if( index === -1 ) 
                {
			alert( "Something gone wrong" );
		}
                $scope.features_list.push({fid:comArr[index].fid,domain:comArr[index].domain,fea: comArr[index].fea})
		$scope.features.splice( index, 1 );
                
                var list_data =[];
                $scope.list.filter(function(l,j){
                    if(l.dfm_id != fid)
                        list_data.push(l);
                });
                $scope.list = list_data;
//                alert(JSON.stringify($scope.list));
                
//                var list_index = -1;
//                $scope.list.filter(function(l,j){
//                    if(l.dfm_id == fid)
//                        list_index = j;
//                });
//                if(list_index != 1)
//                    $scope.list.splice( list_index, 1 );
            };
            $scope.LoadSelectedVehicleVersionData = function() 
            {
                $http({
                    url : 'loadpreviousvehicleversion_data',
                    method : "POST",
                    data : {"vehicleversion_id":$scope.data.vehicleversion}
                })
                .then(function (response, status, headers, config){
                    result_data = JSON.stringify(response.data.vehmod_map_result);
//                    alert(result_data);
                    $scope.vehicle_list = []; 
                    $scope.model_list = [];
//                    var vm_id =[];
//                    $scope.vehicle_list.push({"vehicle_id":"","vehiclename":"Select"});
                    for(var i = 0; i < response.data.vehmod_map_result.length; i++) 
                    {
                         var data= response.data.vehmod_map_result[i];
                         $scope.vehicle_list.push({
                             "vehicle_id":data.vehicle_id,
                             "vehiclename":data.vehiclename,
                         });          
                         $scope.model_list.push({
                             "vehicle_id":data.vehicle_id,
                             "mod":data.modelname.split(","),
                             "model_id":data.model_id.split(","),
                             "vehicle_mapping_id":data.vehicle_mapping_id.split(","),
                         });                         
//                         vm_id.push({"vehicle_mapping_id": data.vehicle_mapping_id.split(",")});
//                         angular.forEach(data.modelname.split(","), function(value, key) {
//                            $scope.model_list.push({
//                             "vehicle_id":data.vehicle_id,
//                             "mod":value,
//                            }); 
//                         })
                    }
//                    alert(JSON.stringify($scope.model_list));
                });
            };
            
            
            //load vehicle and model name
            $scope.LoadVehicleModels= function()
            {
                $http({
                    url : 'loadvehiclemodelname',
                    method : "POST",
                    data : {"pdb_id":$scope.data.pdbversion.pdbid}
                }).then(function (response, status, headers, config){
                    
                    var vm_result = [];
                    $scope.status_value = "";
                    $scope.vehicleresults = "";
                    
                   for(var i = 0; i < response.data.maps_object.pdbversion.length; i++)
                   {
                       $scope.Demo.dt = response.data.maps_object.pdbversion[i];
//                        for (var i = 0; i < $scope.Demo.dt.modelname.split(",").length; i++) {
//                            var arr = $scope.Demo.dt.modelname.split(",");
//                            $window.alert(arr[i]);
//                        }
                        var v = {"vehicle_id" :$scope.Demo.dt.vehicle_id};
                        var m = [];
                        var arr = $scope.Demo.dt.modelname.split(",");
                        var arr1 = $scope.Demo.dt.model_id.split(",");
                        for (var item in arr) {
                            m.push({
                                    "model_id":parseInt(arr1[item]),
                                    "modelname":arr[item]
                                });
                        }
                        v.models = m;
                        $scope.vehicleresults = v;
//                        var data = response.data.maps_object.pdbversion[i];
//                        vm_result.push({
//                            "pdbversion":data.pid,
//                            "vehicleid":data.vid,
//                            "vehiclename":data.vname,
//                            "vehiclemodelid":data.mid,
//                            "vehiclemodelname":data.mname
//                        });
                    }
    //                $scope.Demo.data = [{"vehiclename":"sasdsa","modelname":["dfsd","jhkjk","hkkjhk","kljk"],"versionname":"4.0","status":false}];
                });
//                alert(JSON.stringify($scope.records));
            }
                        
            
            $scope.createfeature_and_domain = function (event) 
            {        
                if (!$scope.doSubmit) 
                {
                    return;
                }
                $scope.doSubmit = false; 
                var feature_and_domain_data = {};
                feature_and_domain_data['domain_name'] = $scope.domain;
                feature_and_domain_data['features_and_description'] = $scope.Demo.data;
                if($scope.Demo.data.length > 0)
                {
                //                        alert(JSON.stringify(feature_and_domain_data));
                       $http({
                       url : 'createfeature_and_domain',
                       method : "POST",
                       data : feature_and_domain_data
                       })
                       .then(function (data, status, headers, config)
                       {
                            result_data = data.data.domainFeatures_result;
                            //result_data =  result_data.slice(1, -1);
                            for(var i = 0; i < result_data.length; i++) 
                            {
                                $scope.features.push({fid:result_data[i].fid,fea:result_data[i].fea,domain:result_data[i].domain});
                            }
                       });
                       $('#modal-product-form').closeModal();
                       $scope.domain="";
                       $scope.Demo.data=[];
                }
                else
                {
                    alert("Please create atleast one features");
                }
            }
            /*$scope.checkNotify = function (event){
            if($scope.data.status && event === "submit"){
                if($scope.list.length > 0){
                    $(".notifyPopup").click();
                }else{
                    alert("Please fill the domain and feature status to create PDB version");
                }
            }else
                $scope.createpdbversion(event);
            }*/  
            
            $scope.radiovalue = function(dfm_id,model_id,status)
            {		
//                alert("enter");
                if($scope.list.length === 0)
                {
                    $scope.list.push({model_id:model_id,dfm_id:dfm_id,status:status});
                }
                else
                {
                    var temp=0;
                    for(var i=0; i<$scope.list.length; i++)
                    {
                        if(($scope.list[i].model_id === model_id) && ($scope.list[i].dfm_id === dfm_id))
                        {
                            $scope.list[i].status=status;
                            temp=1;
                        }
                        
                    }
                    if(temp==0)
                    {
                        $scope.list.push({model_id:model_id,dfm_id:dfm_id,status:status});
                    }
                }
//                alert(JSON.stringify($scope.list))
            };
            $scope.ChooseObtainOrEdit = function(){
//                alert($scope.data.vehicleversion);
//                alert($scope.data.vehiclename);
                if($scope.data.vehicleversion != undefined && $scope.data.vehiclename != undefined){
                    $('#edit_version').openModal();
                }
                else{
                    alert("Please select the vehicleversion and vehiclename");
                    $scope.data.pdbversion = "";
                }
            };
            
            $scope.LoadPDBPreviousVersion = function(data) 
            {               
//                $('#edit_version').openModal();
//                alert(data);
                $http({
                    url : 'loadpdbpreviousvehicleversion_data',
                    method : "POST",
                    data : {"pdbversion_id":$scope.data.pdbversion}
                })
                .then(function (response, status, headers, config){
//                  alert(JSON.stringify(response.data.pdb_map_result,null,4));
                    var result_data = response.data.pdb_map_result;
                    var vehicledetail_list = result_data.vehicledetail_list;
                    if(data === "edit"){
                        $scope.data.status = result_data.pdbversion_status[0].status;
                        $scope.data.vehicleversion = vehicledetail_list[0].vehver_id.toString();
                        $scope.LoadSelectedVehicleVersionData();
                        $scope.data.vehiclename = vehicledetail_list[0].vehicle_id.toString();
                        $scope.records = vehicledetail_list;
                    }
                    $scope.list = [];
                    
                    for(var i=0; i<$scope.features.length; i++)                        
                        $scope.features_list.push({fid:$scope.features[i].fid,domain:$scope.features[i].domain,fea: $scope.features[i].fea})
                    
                    $scope.features = [];
//                    alert(JSON.stringify($scope.records));                    
                    var featuredetail_list = result_data.featuredetail_list;
                    for(var i=0; i<featuredetail_list.length; i++)
                    {
                        if($scope.features.length === 0)
                        {
                            $scope.add_feature_tab(featuredetail_list[i].fid);
//                            $scope.features.push({fid:featuredetail_list[i].fid,fea:featuredetail_list[i].featurename,domain:featuredetail_list[i].domainname,status:featuredetail_list[i].status});
                        }
                        else
                        {
                            var temp=0;
                            for(var j=0; j<$scope.features.length; j++)
                            {
                                if($scope.features[j].fid === featuredetail_list[i].fid)
                                {
                                    temp=1;
                                }   
                            }
                            if(temp==0)
                            {
                                $scope.add_feature_tab(featuredetail_list[i].fid);
                            }
                        }
                        if(data === "edit")
                            $scope.radiovalue(featuredetail_list[i].fid,featuredetail_list[i].model_id,featuredetail_list[i].status);
//                        alert(JSON.stringify($scope.list));  
                    }
                    if(data === "edit"){
                        angular.element(function () {
                            var result = document.getElementsByClassName("radio_button");
                            angular.forEach(result, function(value) {
                                var result_name = value.getAttribute("name").substring(1).split("_");
                                var fid = result_name[0];
                                var model_id = result_name[1];
                                var status = value.getAttribute("value");  
                                angular.forEach($scope.list, function(item) {
                                    if(item.dfm_id == fid && item.model_id == model_id && item.status == status)
                                        value.setAttribute("checked","checked");
                                });    
                            });
                        });
                    }
                });
            };
            
            
            //getting pdb version and id
            $scope.LoadPreviousVersion = function()
            {
                $http({
                    url : 'loadpdbversion_data',
                    method : "POST",
                    data : {"vehicleversion_id":$scope.data.vehicle}
                }).then(function (response, status, headers, config){
                    
                    $scope.array_result = [];
                    $scope.status_value = "";
                    var pdbLength = response.data.maps_object.pdbversion.length;
                   for(var i = 0; i < pdbLength; i++)
                   {
                        var data= response.data.maps_object.pdbversion[i];
                        $scope.array_result.push({
                            "pdbid":data.pid,
                            "pdbversion":data.pversion
                        });
                    }
                    data.pdbversion = "1";
//                    $scope.Demo.data = array_result;
    //                $scope.Demo.data = [{"vehiclename":"sasdsa","modelname":["dfsd","jhkjk","hkkjhk","kljk"],"versionname":"4.0","status":false}];
                });
            };
            
            if($location.absUrl().includes("?")){
                var params_array = [];
                var absUrl = $location.absUrl().split("?")[1].split("&");
                for(i=0;i<absUrl.length;i++){
                    var key_test = absUrl[i].split("=")[0];
                    var value = absUrl[i].split("=")[1];
//                    alert(key_test);
//                    alert(value);
                    params_array.push({[key_test]:value});
                }
                $scope.data.pdbversion = params_array[0].id;
                var action = params_array[1].action;
                
                var result_data = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
                var vehicledetail_list = result_data.vehicledetail_list;
                $scope.data.status = result_data.pdbversion_status[0].status;
                
                $scope.data.vehicleversion = vehicledetail_list[0].vehver_id.toString();
                $scope.LoadSelectedVehicleVersionData();
                $scope.data.vehiclename = vehicledetail_list[0].vehicle_id.toString();
                $scope.records = vehicledetail_list;
//                    alert(JSON.stringify($scope.records));                    
                var featuredetail_list = result_data.featuredetail_list;
                for(var i=0; i<featuredetail_list.length; i++)
                {
                    if($scope.features.length === 0)
                    {
                        $scope.add_feature_tab(featuredetail_list[i].fid);
//                            $scope.features.push({fid:featuredetail_list[i].fid,fea:featuredetail_list[i].featurename,domain:featuredetail_list[i].domainname,status:featuredetail_list[i].status});
                    }
                    else
                    {
                        var temp=0;
                        for(var j=0; j<$scope.features.length; j++)
                        {
                            if($scope.features[j].fid === featuredetail_list[i].fid)
                            {
                                temp=1;
                            }   
                        }
                        if(temp==0)
                        {
                            $scope.add_feature_tab(featuredetail_list[i].fid);
                        }
                    }

                    $scope.radiovalue(featuredetail_list[i].fid,featuredetail_list[i].model_id,featuredetail_list[i].status);
//                        alert(JSON.stringify($scope.list));  
                }
                angular.element(function () {
                    var result = document.getElementsByClassName("radio_button");
                    angular.forEach(result, function(value) {
                        var result_name = value.getAttribute("name").substring(1).split("_");
                        var fid = result_name[0];
                        var model_id = result_name[1];
                        var status = value.getAttribute("value");  
                        angular.forEach($scope.list, function(item) {
                            if(item.dfm_id == fid && item.model_id == model_id && item.status == status)
                                value.setAttribute("checked","checked");
                        });    
                    });
                });
                if(action == "view"){
                    $scope.showSave =false;
                    $scope.showSubmit =false;
                }
            }    
        });
        
        app.controller('OperationCtrl',['$scope','$http','$rootScope', function ($scope, $http,$rootScope)
        {
            //$scope.generate = "obtain";
            $scope.proceed = function(){       
//                alert($scope.generate);
                if($scope.generate != undefined){
                    $(".modal-close").click();
                    //alert($scope.generate);
                    //$rootScope.$emit("CallLoadPDBPreviousVersion", {'selectedvalue':$scope.generate});
                    $rootScope.$broadcast('CallLoadPDBPreviousVersion', $scope.generate);
                    //$rootScope.LoadPDBPreviousVersion();
                }
                else{
                    alert("Select atleast one");
                }
            }; 
        }]);
        
        app.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;
                  
                  element.bind('change', function(){
                     scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
         }]);
      
         app.service('fileUpload', ['$http','$window', function ($http,$window) {
            this.uploadFileToUrl = function(file, uploadUrl){
               var fd = new FormData();
               fd.append('file', file);
            
               $http.post(uploadUrl, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
               }).then(function success(response) {
                        $(".loader-block").hide();
                        alert(response.data.status);  
                        $window.open("pdb_listing.action","_self");
                    }, function error(response) {
                        $(".loader-block").hide();
                        alert("Server Error while Importing");
                    })
            }
         }]);
         
         app.controller('fileCtrl', ['$scope', 'fileUpload','$window', function($scope, fileUpload, $window){
            $scope.uploadFile = function(){
                var file = $scope.myFile;
                if(file != undefined){
                    $(".loader-block").show();
    //                alert('hi');               
                   //console.log('file is ' );
                   //console.dir(file);

                   var uploadUrl = "pdbImport";
                   fileUpload.uploadFileToUrl(file, uploadUrl);
    //               alert("after file upload");
//                   $window.open("pdb_listing.action","_self");
               }
               else{
                   alert("Please upload CSV file for import");
               }
            };
         }]);

    $(document).ready(function(){
        // initialize modal
        $('.modal-trigger').leanModal();
        
    });
    </script> 
    
</body>

</html>          
