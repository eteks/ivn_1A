<%@include file="header.jsp" %>
<%@include file="sidebar.jsp" %>
<!--<base href="/">-->
<div class="pcoded-content" ng-controller="MyCtrl as Demo">
    <div class="pcoded-inner-content">
        <div class="main-body">

            <div class="page-wrapper">
                <div class="page-header card">
                    <div class="row align-items-end">
                        <div class="col-lg-8">
                            <div class="page-header-title">
                                <i class="icofont icofont-car-alt-2 bg-c-red"></i>
                                <div class="d-inline">
                                    <h4>Vehicle version</h4>
                                    <span>Add vehicle and Models</span>
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
                                        <s:url action="ivn_supervisor.action" var="aURL" />
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
                                                                            <input type="radio" id="vehicle" ng-model="new_vehicle" value="select_vehicle"/>
                                                                        </div>
                                                                        <div class="form-group"  ng-if="new_vehicle=='select_vehicle'">
                                                                            <label for="vehiclename">Vehicle:</label>
                                                                            <select id="vehiclename" ng-model="data.vehicleversion" ng-change="LoadPreviousVersion()">
                                                                                <s:iterator value="vehicleversion_result" var="data" >
                                                                                    <option value="<s:property value="id"/>"><s:property value="vehiclename"/></option>
                                                                                </s:iterator>
                                                                            </select>
                                                                            <label for="version">Version:</label>
                                                                            <select id="version" ng-model="data.vehicleversion" ng-change="LoadPreviousVersion()">
                                                                                <s:iterator value="vehicleversion_results" >
                                                                                    <option value="<s:property value="id"/>"><s:property value="versionname"/></option>
                                                                                </s:iterator>
                                                                            </select>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="vehicle">New vehicle:</label>
                                                                            <input type="radio" ng-model="new_vehicle" value="new_vehicle"/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="vehicle">Vehicle:</label>
                                                                            <input type="text" class="form-control" placeholder="Enter vehicle" name="vehicle"  ng-model="data.vehiclename" required>
                                                                            <span ng-show="myForm.vehicle.$touched && myForm.vehicle.$invalid">The name is required.</span>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="model">Model:</label>
                                                                            <tags-input ng-model="data.modelname"  use-strings="true"></tags-input>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 float-left">
                                                <div class="card">
                                                    <div class="row card-block marketing-card">
                                                        <div class="col-md-12 mod_vec_animate" ng-if="Demo.data">
                                                            <h5 class="m-t-20"><i class="icofont icofont-steering"></i> {{data.vehiclename}}</h5>
                                                            <ul>
                                                                <li ng-repeat="i in data.modelname">

                                                                    <i class="icofont icofont-whisle text-c-red"></i> {{i}}
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
                                                                       <input type="radio" ng-click="radiovalue(record.fid,i.vehicle_model_mapping_id,'y')" name="f{{record.fid}}_{{i.vehicle_model_mapping_id}}" value="y" class="radio_button">
                                                                       <span class="checkmark c_b_g">
                                                                       </span>
                                                                       <span class="tooltip-content2">yes</span>
                                                                     </label>
                                                                     <label class="custom_radio mytooltip tooltip-effect-8">
                                                                       <input type="radio" ng-click="radiovalue(record.fid,i.vehicle_model_mapping_id,'n')" name="f{{record.fid}}_{{i.vehicle_model_mapping_id}}" value="n" class="radio_button">
                                                                       <span class="checkmark c_b_r"></span>
                                                                       <span class="tooltip-content2">no</span>
                                                                     </label>
                                                                     <label class="custom_radio mytooltip tooltip-effect-8">
                                                                       <input type="radio" ng-click="radiovalue(record.fid,i.vehicle_model_mapping_id,'o')" name="f{{record.fid}}_{{i.vehicle_model_mapping_id}}" value="o" class="radio_button">
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

                                               <button ng-show="showSave == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createpdbversion('save')" name="save">Save</button>
                                               <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createpdbversion('submit')" name="submit">Submit</button>

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
<!--<base href="/">-->
<%@include file="footer.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular-animate.js"></script>
<script>
//    var app = angular.module('myApp', ['ngTagsInput']);
//    app.config( [ '$locationProvider', function( $locationProvider ) {
//        // In order to get the query string from the
//        // $location object, it must be in HTML5 mode.
//        $locationProvider.html5Mode( true );
//     }]);
    app.controller('MyCtrl',function($scope, $http ,$window, $location)
    {
        
        
        this.data = [];
        var notification_to;
            $scope.showSave =true;
            $scope.showSubmit =true;
            $scope.$on('notifyValue', function (event, args) {
                notification_to = args;
                $scope.createVehicleVersionAjax("submit");
            });
          $scope.data = {};
          alert("<s:property value="maps_object.features"/>");
          $scope.features_list = [{"fid":"1","fea":"FRT MNL A/C ON","domain":"AIR CONDITIONER"},{"fid":"2","fea":"FRT AUTO A/C ON (DUAL ZONE)","domain":"AIR CONDITIONER"}];
          
          $scope.createpdbversion = function (event)
          {
            var status = true;
            var data = {};
            data['pdbversion'] = {"vehicle_id": "1", "pdb_manual_comment":"test", "status": true};
//            data['pdbversion'] = {"vehicle_id": "1", "pdb_manual_comment":"test", "status": true,"pdbversion_id": "1", "pdbversion_name":"1.0"};
            data['pdbdata_list'] = [{"model_id":"1","dfm_id":"1","status":"y"},{"model_id":"1","dfm_id":"2","status":"n"},{"model_id":"1","dfm_id":"3","status":"o"},{"model_id":"2","dfm_id":"1","status":"o"},{"model_id":"2","dfm_id":"2","status":"n"},{"model_id":"2","dfm_id":"3","status":"y"},{"model_id":"3","dfm_id":"1","status":"y"},{"model_id":"3","dfm_id":"2","status":"n"},{"model_id":"3","dfm_id":"3","status":"o"}];
            data['button_type'] = event;
            data['dfm_set'] = ["1","2","3"];
            alert(JSON.stringify(data));
            $http({
                url: 'createpdbversion',
                method: "POST",
                data: data,
            }).then(function (data, status, headers, config) {
//                                    $window.alert(JSON.stringify(data));
//                                      alert(JSON.stringify(data.data.maps.status).slice(1, -1));
//                                      $window.open("pdb_listing.action","_self"); //                alert(data.maps);
//            //                        Materialize.toast(data['maps']["status"], 4000);
            });
          }

          if($location.absUrl().includes("?")){
                var params_array = [];
                var absUrl = $location.absUrl().split("?")[1].split("&");
//                alert(absUrl);
                for(i=0;i<absUrl.length;i++){
                    var key_test = absUrl[i].split("=")[0];
                    var value = absUrl[i].split("=")[1];
//                    alert(key_test);
//                    alert(value);
                    params_array.push({[key_test]:value});
                }
              $scope.data.vehicleversion = params_array[0].id;
//               alert(JSON.stringify(params_array));
              var action = params_array[1].action;
              var result_data = JSON.parse("<s:property value="vehmod_map_result_obj"/>".replace(/&quot;/g,'"'));
    //        result_data = [{"vehicle_mapping_id":"1,2","vehiclename":"vehicle1","modelname":"v11,v12","model_id":"1,2","versionname":"1.0","vehicle_id":1,"status":true},{"vehicle_mapping_id":"3,4,5","vehiclename":"vehicle2","modelname":"v21,v22,v23","model_id":"3,4,5","versionname":"1.0","vehicle_id":2,"status":true}];
            var array_result = [];
            var status_value = "";
            for(var i = 0; i < result_data.length; i++)
            {
                 var data= result_data[i];
                 array_result.push({
                     "vehiclename":data.vehiclename,
                     "modelname":data.modelname.split(","),
                     "versionname":data.versionname,
                     "status":data.status
                 });
                 status_value = data.status;
             }
             $scope.Demo.data = array_result;
             $scope.data.status = status_value;

             if(action == "view"){
                 $scope.showSave =false;
                $scope.showSubmit =false;
             }
         }
        /*$scope.checkNotify = function (event){
            if($scope.data.status && event === "submit"){
                if($scope.Demo.data.length > 0){
                    $(".notifyPopup").click();
                }else{
                    alert("Please fill all the fields");
                }
            }else
                $scope.submit_vehicleversion(event);
        }*/

        $scope.createVehicleVersionAjax = function(event){
            var status = $scope.data.status;
            if(status == undefined || status == false)
                notification_to = undefined;

            var data = {};
            data['vehicle_and_model'] = $scope.Demo.data;
            data['vehicleversion'] = $scope.data;
            data['button_type'] = event;
            data['notification_to'] = notification_to+"";

            $http({
                url : 'createvehicleversion',
                method : "POST",
                data : data
                })
                .then(function (data, status, headers, config){
                      alert(JSON.stringify(data.data.maps.status).slice(1, -1));
                      $window.open("vehicleversion_listing.action","_self"); //                alert(data.maps);
        //                Materialize.toast(data['maps']["status"], 4000);
            });
        }

        $scope.submit_vehicleversion = function (event){
            var status = $scope.data.status;
            if(status == undefined )
                status = false;

            if (!$scope.doSubmit) {
                return;
            }
            $scope.doSubmit = false;

            if($scope.Demo.data.length > 0){
                if(status && event === "submit"){
                    $(".notifyPopup").click();
                }else
                    $scope.createVehicleVersionAjax(event);
            }
            else{
                alert("Please fill all the fields");
            }
//            
////            for (var key in $scope.Demo.data) 
////            {
////                for (var i = 0; i < $scope.Demo.data[key].length; i++) 
////                {
////                    var title = $scope.Demo.data[key][i].vehiclename;
////                    var desc = $scope.Demo.data[key][i].modelname;
////                    var badge = document.createElement('div');
////                    badge.className = 'badge';
////                    badge.innerHTML =
////                    '<h1>' + title + '</h1>' +
////                    '<h2>' + desc + '</h1>' +
////                    '<div class="options-only-phone">' +
////                    '<a class="service-provider-call" href="#" target="_blank"> Buy for $' + price + '</a>';
////                    document.getElementById('basketball'').appendChild(badge);
////                }
////            }
        }
        $scope.tabstep1 = function()
        {
//            alert('hi');
        }
        $scope.tabstep2 = function()
        {
//            alert('hi');
        }

        //
        $scope.LoadPreviousVersion = function()
        {
//            alert("loadpreviousversion");
//            alert($scope.data.vehicleversion);
            $http({
                url : 'loadpreviousvehicleversion_data',
                method : "POST",
                data : {"vehicleversion_id":$scope.data.vehicleversion}
            })
            .then(function (response, status, headers, config){
//                result_data = JSON.stringify(response.data.vehmod_map_result);
               var array_result = [];
               var status_value = "";
               for(var i = 0; i < response.data.vehmod_map_result.length; i++)
               {
                    var data= response.data.vehmod_map_result[i];
                    array_result.push({
                        "vehiclename":data.vehiclename,
                        "modelname":data.modelname.split(","),
                        "versionname":data.versionname,
                        "status":data.status
                    });
                    status_value = data.status;
                }
                $scope.Demo.data = array_result;
                $scope.data.status = status_value;
//                $scope.Demo.data = [{"vehiclename":"sasdsa","modelname":["dfsd","jhkjk","hkkjhk","kljk"],"versionname":"4.0","status":false}];
            });
        };

    });
//     var m = angular.module('App',['ngAnimate']);
</script>

</body>

</html>