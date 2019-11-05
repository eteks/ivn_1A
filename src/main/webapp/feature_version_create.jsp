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
                                                        <h4>Feature Version</h4>
                                                        <span>Create</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="page-header-breadcrumb">
                                                    <ul class="breadcrumb-title">
                                                        <li class="breadcrumb-item">
                                                            <a href="index.html"> <i class="icofont icofont-home"></i></a>
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
                                                    <div class="card-block marketing-card p-t-0 text-center">
                                                         <div class="row p-t-30">                                                            
                                                            <div class="form-group col-md-3">
                                                                <label for="vehiclename"> Select Vehicle:</label>
                                                                <select id="vehiclename" ng-model="data.vehicle" ng-change="LoadPreviousVersion()" >
                                                                    <option value="">Select Vehicle</option>
                                                                    <s:iterator value="vehicleversion_result" var="data" >
                                                                        <option value="<s:property value="id"/>"><s:property value="vehiclename"/></option>
                                                                    </s:iterator>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="feature">Previous Feature version :</label>
                                                                <select id="feature" ng-model="data.featureversion" ng-options="arr as arr.name for arr in feaarray_result" disabled>
                                                                </select>
                                                            </div>
                                                        </div>   
                                                            
                                                        <!--drag and drop-->
                                                        <script type="text/ng-template" id="list.html">
                                                            <div ng-repeat="list in lists" class="col-md-4 drop_list_box">
                                                                <div class="panel panel-info">
                                                                  <div class="panel-heading">
                                                                    <h5 class="panel-title">List of {{list.label}} </h5>
                                                                  </div>
                                                                  <ul dnd-list="list.version"
                                                                            dnd-allowed-types="list.allowedTypes"
                                                                            dnd-disable-if="list.version.length == list.max-1">

                                                                            <li ng-repeat="person in list.version"
                                                                                dnd-draggable="person"
                                                                                dnd-type="person.type"
                                                                                dnd-disable-if="person.type == 'unknown'"
                                                                                dnd-moved="list.version.splice($index, 1)"
                                                                                class="background-{{person.type}}"
                                                                                >
                                                                                {{person.name}}
                                                                            </li>

                                                                            <li class="dndPlaceholder">
                                                                                Drop any <strong>{{list.allowedTypes.join(' or ')}}</strong> here
                                                                            </li>

                                                                        </ul>
                                                                </div>                                                              
                                                              </div>
                                                              <div class="check_feature">
                                                              <!--{{models.dropzones.B[3].version[0].type}}-->
                                                                      <button type="submit" class="btn btn-grd-success" ng-mousedown='doSubmit=true' ng-click="checkcompatibility()" name="save">Check Feature Compatibility</button>
                                                               </div>
                                                           </script> 

                                                          <!-- This template is responsible for rendering a container element. It uses
                                                           the above list template to render each container column -->
                                                          <script type="text/ng-template" id="container.html">
                                                            <div class="container-element box box-blue">
                                                              <h3>Container {{item.id}}</h3>
                                                              <div style="padding:125px" class="column" ng-repeat="lists in item.columns" ng-include="'list.html'"></div>

                                                            </div>
                                                          </script>

                                                          <!-- Template for a normal list item -->
                                                          <script type="text/ng-template" id="item.html">
                                                            <div class="item">Item {{item.id}}</div>
                                                          </script>

                                                          <!-- Main area with dropzones and source code -->
                                                          <div class="nestedDemo">
                                                            <div class="col-sm-12">
                                                              <div class="row">
                                                                <div ng-repeat="(zone, lists) in models.dropzones" class="col-md-12">
                                                                  <div class="dropzone box box-yellow">
                                                                    <!-- The dropzone also uses the list template -->
                                                                    <h3>Dropzone</h3>
                                                                    <div ng-include="'list.html'"></div>
                                                                  </div>
                                                                </div>
                                                              </div>

                                                              <div view-source="nested" ></div>

                                                              <h2>Generated Model</h2>
                                                              <pre class="text-left">{{modelAsJson}}</pre>
                                                            </div>

                                                            <!-- Sidebar -->

                                                          </div>
                                                        <!--end drag and drop-->
                                                        
                                                    </div>
                                                </div>
                                            </div>
                <div class="text-right p-10">
                    <label for="status" style="vertical-align:middle">Status:</label>
                    <label class="switch m-r-50"  style="vertical-align:middle">
                        <input type="checkbox" ng-model="data.status">
                        <span class="slider round"></span>
                     </label>
                    <a class="modal-trigger btn-floating btn-primary" ng-show="showProceed == true" style="padding:10px" href="#modal-comment" >Proceed</a>
                    <div id="modal-comment" class="modal">
                         <div class="modal-content text-left">

                             <h5 class="text-c-red m-b-10">Comment <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                             <textarea class="col-md-12 m-b-10" ng-model="data.featureversion_manual_comment"></textarea>
                            <div ng-if="create_type == true" class="form-radio">
                                <div class="radio radio-inline">
                                    <label>
                                        <input type="radio" ng-click="" ng-model="data.version_change" value="major" class="radio_button">
                                        <i class="helper"></i>Major
                                    </label>
                                </div>
                                <div class="radio radio-inline">
                                    <label>
                                        <input type="radio" ng-click="" ng-model="data.version_change" value="minor" class="radio_button">
                                        <i class="helper"></i>Minor
                                    </label>
                                </div>                                                            
                            </div>
                             <div class="text-right">
                                 <button ng-show="showSave == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createfeatureversion('save')" name="save">Save</button>
                                 <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createfeatureversion('submit')" name="submit">Submit</button>
                             </div>
                         </div>
                     </div>
                </div>
                <!-- Marketing End -->
                        
             
                <%@include file="footer.jsp" %>
                <!--<script src="js/lib/materialize.min.js"></script>-->
                <!--<script src="js/dirPagination.js"></script>-->
                        
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>-->
  <!--<script src="js/dirPagination.js"></script>-->
    <script>
//        var app = angular.module('angularTable', ['angularUtils.directives.dirPagination']);

        app.controller('RecordCtrl1', function($scope, $http, $window, $location, $element, $rootScope)
        {
            this.data=[];
            var notification_to;
            $scope.features = [];
            $scope.list = [];
            $scope.Demo.dt = [];
            $scope.vehicleresults = {};
            $scope.vercompare_results = {};            
            $scope.truefalse = false;
            $scope.records = [];
            $scope.create_type = false;
            $scope.showSave =true;
            $scope.showProceed =true;
            $scope.showSubmit =true;
            $scope.data = {};
             $scope.dropCallback = function(index, item, external, type) {
                // Return false here to cancel drop. Return true if you insert the item yourself.
                // roll down and delete any empty columns//
                var model = $scope.models.dropzones;
                for (var y in model.B) {
                  for (var zz in model.B[y].columns) {
                    var myColumns = [];
                    var foundThem = false;
                    if (Array.isArray(model.B[y].columns[zz])) {
                      $scope.models.dropzones.B[y].columns.splice(zz, 1);
                    } 
                  }
                }

                return item;
              };

              $scope.models = {
                selected: null,
                templates: [{
                  type: "item",
                  id: 2
                }, {
                  type: "container",
                  id: 1,
                  columns: [
                    []
                  ]
                }],
                dropzones: {
                  "B": [
                        {
                          label: "PDB",
                          allowedTypes: ['pdb'],
                          max: 3,
//                          version: [
//                              {name: "PDB 1.0", type: "pdb"},
//                              {name: "PDB 2.0", type: "pdb"},
//                              {name: "PDB 3.0", type: "pdb"}
//                          ]
                          version:[]
                      },
                      {
                          label: "Safety",
                          allowedTypes: ['safety'],
                          max: 3,
//                          version: [
//                              {name: "Safety 1.0", type: "safety"},
//                              {name: "Safety 2.0", type: "safety"},
//                              {name: "Safety 3.0", type: "safety"}
//                          ]
                          version:[]
                      },
                      {
                          label: "Legislation",
                          allowedTypes: ['legislation'],
                          max: 3,
//                          version: [
//                              {name: "Legislation 1.0", type: "legislation"},
//                              {name: "Legislation 2.0", type: "legislation"},
//                              {name: "Legislation 3.0", type: "legislation"}
//                          ]
                         version:[]
                      },
                      {
                          label: "Feature version",
                          allowedTypes: ['pdb', 'safety','legislation'],
                          max: 4,
                          version: []
                      }
                  ]
                }
              };
//              alert(JSON.stringify($scope.models.dropzones.B[0].version));

              $scope.$watch('models.dropzones', function(model) {
                $scope.modelAsJson = angular.toJson(model, true);
              }, true);      
            $scope.checkcompatibility = function()
            {
                if($scope.models.dropzones.B[3].version[0].type == "pdb" || $scope.models.dropzones.B[3].version[1].type == "pdb")
                {
                    alert('Features are compatible with safety and legistion');
                }
                else
                {
                    alert('Features are not compatible with safety and legistion');
                }
            }
            $scope.LoadPreviousVersion = function()
            {
                $http({
                    url : 'loadpdb_safety_leg_version',
                    method : "POST",
                    data : {"vehicle_id":$scope.data.vehicle}
                }).then(function (response, status, headers, config){
                    
                    if (response.data.maps_string.success) {
                        
                        alert(response.data.maps_string.success);
                        $scope.models.dropzones.B[0].version = response.data.maps_object.pdb_results;
                        $scope.models.dropzones.B[1].version = response.data.maps_object.saf_results;
                        $scope.models.dropzones.B[2].version = response.data.maps_object.leg_results;
                        
                        if (response.data.maps_object.fea_results) {
                            $scope.feaarray_result = response.data.maps_object.fea_results;
                            $scope.create_type = true;
                            $scope.data.featureversion = $scope.feaarray_result[0];
                        }                        
                    } else {
                        alert(response.data.maps_string.error);
                    }
                    
                });
            };
            
            $scope.$on('notifyValue', function (event, args) {
                notification_to = args;
                $scope.createfeatureAjax("submit");
            });
            
            $scope.createfeatureversion = function (event)
            {
//                alert("createfeatureversion");
                var status = $scope.data.status;
                if(status == undefined )
                    status = false;
                if($scope.models.dropzones.B[3].version.length > 0){
                    if(status && event == "submit"){
                        $(".notifyPopup").click();
                    } else {
                        $scope.createfeatureAjax(event);
                    }
                }
                else{
                    alert("Please drop the versions to create feature version");
                }
            };
            
            $scope.createfeatureAjax = function (event){
                
                var status = $scope.data.status;
                if(status == undefined || status == false)
                    notification_to = undefined;
                var data = {};
//                $scope.data.vehicle_id = $scope.vehicleresults.vehicle_id;
                data['featureversion'] = $scope.data;
//                data['pdbdata_list'] = $scope.list;
                data['featuredata_list'] = $scope.models.dropzones.B[3].version;
                data['button_type'] = event;
                data['notification_to'] = notification_to+"";
//                alert(JSON.stringify(data));
                $http({
                    url: 'createfeatureversion',
                    method: "POST",
                    data: data,
                }).then(function (response, status, headers, config) {
////                    $scope.vercompare_results = {"removed_features":"(d1) feature3, (d1) feature5", 
////                                                 "added_features":"(d1) feature4", 
////                                                 "removed_models":"m2,m4", "added_models":"m3", 
////                                                 "previous_version":"1.0", "current_version":"1.1"
////                                                };

                    if(response.data.maps_string.status_code == "1") {
                        
                        if(response.data.maps_object.fea_previous_data_result){
                            $scope.vercompare_results = response.data.maps_object.fea_previous_data_result;
                            alert(JSON.stringify($scope.vercompare_results));    
                        }
                        else{
                            alert("No any previous version found to compare");
                        }
                        var fea = JSON.parse(response.data.maps_string.fea_version.replace(/&quot;/g,'"'));
//                        alert(fea);
                        fea['froms'] = "Feature";
                        fea['t_id'] = $scope.t_id;
                        fea['tg_id'] = $scope.tg_id;
                        alert("leg "+ JSON.stringify(fea));
                        $http({
                            url: 'insertTasks',
                            method: "POST",
                            data: {"val":fea},
                        }).then(function (response, status, headers, config) {
                            if (response.data.maps_object.success) {
                                alert(response.data.maps_object.success);
                                console.log(response.data.maps_object.success+" "+JSON.stringify(response.data.list_object));
                            } else {
                                alert(JSON.stringify(response.data.maps_object));
                            }
                        });
                        $window.open("safety_list.action","_self");
                    } else {                        
                        alert(response.data.maps_string.status);
                    }
                });
                
            };

            if($location.absUrl().includes("?")) {

                var params_array = [];
                var absUrl = $location.absUrl().split("?")[1].split("&");
                for(i=0;i<absUrl.length;i++){
                    var key_test = absUrl[i].split("=")[0];
                    var value = absUrl[i].split("=")[1];
//                    alert(key_test);
//                    alert(value);
                    params_array.push({[key_test]:value});
                }

                if (params_array[0].id && params_array[1].action) {
                    $scope.data.pdbversion = params_array[0].id;
                    var action = params_array[1].action;
                    var maps_object = {};
                    if ("<s:property value="result_data_obj"/>") {

                        maps_object = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
//                        alert("fasdfjksaf  "+JSON.stringify(maps_object));
                        for (var item in maps_object) {
                            
                            if (maps_object[item].type == "vehicle") {
                                
                                var a = [maps_object[item]];
                                $scope.data.vehicle = a[0].id.toString();
//                                alert(JSON.stringify(a));
                            }
                            if (maps_object[item].type == "feature") {
                                
                                $scope.data.status = maps_object[item].status;
                                $scope.feaarray_result = [maps_object[item]];
                                $scope.create_type = true;
                                $scope.data.featureversion = $scope.feaarray_result[0];
//                                alert(JSON.stringify($scope.feaarray_result));
                            }
                        }
                        maps_object = maps_object.filter(m => m['type'] !== "vehicle");
                        $scope.models.dropzones.B[3].version = maps_object.filter(m => m['type'] !== "feature");
                    } else
                        alert("Data not Found");

                    if(action == "view"){
                        $scope.showProceed =false;
                        $scope.showSave =false;
                        $scope.showSubmit =false;
                    } else if(action == "edit"){
                        $scope.showProceed =true;
                    }
                } else {
                    $scope.t_id = params_array[0].t_id;
                    $scope.tg_id = params_array[1].tg_id;
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
