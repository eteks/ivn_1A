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
                                                    <i class="icofont icofont-automation bg-c-red"></i>
                                                    <div class="d-inline">
                                                        <h4>ACB</h4>
                                                        <span>ACB Version Create</span>                                 
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
                                                            <s:url action="acb.action" var="aURL" />
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
                                                         <div class="row p-t-30">
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Feature version :</label>
<!--                                                                <select ng-model="data.pdbversion" ng-options="arr as arr.pdbversionname for arr in pdbversion" ng-change="LoadVehicleData()">
                                                                </select>-->
                                                                <select ng-model="data.featureversion" ng-options="arr as arr.versionname for arr in array_result" ng-change="LoadSelectedFeatureVersionData()">
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Vehicle:</label>
                                                                <select ng-model="data.vehiclename" ng-options="arr as arr.vname for arr in arr_res" ng-change="LoadIVNVersion()">
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">IVN version:</label>
                                                                <select ng-model="data.vername" ng-options="arr as arr.ivn_version for arr in records track by arr.ivn_version" >
                                                                </select>
                                                                <button class="text-c-green" style="font-weight:600" ng-click="exportACB()">Export</button>
                                                            </div>
<!--                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">ACB version :</label>
                                                                <select ng-model="data.acbversion" ng-focus="focusCallback($event)" ng-change="LoadACBPreviousVersion($event)" data="mainversion">
                                                                    <s:iterator value="acbversion_result" >
                                                                        <option value="<s:property value="id"/>">
                                                                            <s:property value="acb_versionname"/>
                                                                        </option>
                                                                    </s:iterator>
                                                                </select>
                                                                <select ng-change="LoadACBPreviousVersion($event)" ng-focus="focusCallback($event)" ng-if="acbsubversion.length > 0" ng-model="data.acbsubversion" data="subversion">
                                                                    <option value="{{acb.id}}" ng-repeat="acb in acbsubversion">{{acb.acb_versionname}}</option>                                                                    
                                                                </select>
                                                            </div>                                -->         
                                                        </div>   
                                                        <div class="col-lg-12">
                                                            <div class="ng-table-scrollcontainer">
                                                            <table st-table="rowCollection" class="table table-striped">
                                                                <thead>
                                                                    <tr>   
                                                                        <th class="ng-table-fixedcolumn">Features</th>
                                                                        <th class="text-center" ng-repeat="i in modals">
                                                                            {{i.modelname}}
                                                                        </th>
                                                                        <th class="text-center">Assigned</th>
                                                                        <th class="">ECU</th>
                                                                    </tr>
                                                                </thead>
                                                                <tr dir-paginate="record in features|orderBy:sortKey:reverse|filter:search|itemsPerPage:20">                                                                        
                                                                    <td class="ng-table-fixedcolumn">
                                                                        <a class="modal-trigger" href="#modal-product-form" style="text-decoration:underline;" ng-click="assignstart(record.fid)">
                                                                            <span class="compresslength" style="display:block">{{record.featurename}}</span>
                                                                        </a>
                                                                        
                                                                    </td>
<!--                                                                    <td class="text-center" ng-repeat="x in (record.stat | customSplitString)">-->
                                                                    <!--<td class="text-center acb_btn" ng-repeat="x in (record.status | customSplitString) track by $index">-->
                                                                    
                                                                    <td class="text-center acb_btn" ng-repeat="x in (record.status | customSplitString) track by $index">
                                                                        
                                                                        <span class="btn yellow btn-icon" ng-if="x == 'O'">{{x | uppercase}}</span>
                                                                        <span class="btn green  btn-icon" ng-if="x == 'Y'">{{x | uppercase}}</span>
                                                                        <span class="btn brown btn-icon" ng-if="x == 'N'">{{x | uppercase}}</span>
                                                                    </td>
                                                                    <td class="text-center" ng-if="record.touch != 'No'">
                                                                        <!--{{record.touch}}-->
                                                                        <i class="icofont icofont-ui-check text-c-green"></i>
                                                                    </td>
                                                                    <td class="text-center" ng-if="record.touch == 'No'">
                                                                        <!--No-->
                                                                        <i class="icofont icofont-ui-close text-c-red"></i>
                                                                    </td>
                                                                    <td class="">
                                                                        <span ng-if="record.ecu">{{record.ecu}}</span>
                                                                    </td>
                                                                </tr>
                                                                <tbody>
                                                                <form ng-model="myform">    
                                                                    
                                                                </form>
                                                                </tbody>
                                                            </table>
                                                            </div> 
                                                            <dir-pagination-controls
                                                                    max-size="20"
                                                                    direction-links="true"
                                                                    boundary-links="true" >
                                                            </dir-pagination-controls>    
                                                            <!--<button type="" class="btn grn-btn" ng-click="Confirm()"">Confirm Admin</button>-->
                                                            
                                                        </div>                                               
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Marketing End -->
            
            
            <div id="modal-product-form" class="modal acb_space_drop">
                <!--drag and drop-->
                        <script type="text/ng-template" id="list.html">
                            <div ng-repeat="list in lists" class="col-md-3 drop_list_box ">
                              <div class="panel panel-info {{list.slot}}">
                                <div class="panel-heading">
                                  <h5 class="panel-title">List of {{list.label}} </h5>
                                </div>
                                <ul dnd-list="list.version"
                                          dnd-allowed-types="list.allowedTypes"
                                          dnd-disable-if="list.version.length == list.max-1">
                                          <li ng-repeat="person in list.version"
                                              dnd-draggable="person"                                             
                                              dnd-type="person.type"
                                              dnd-moved="list.version.splice($index, 1)"
                                              dnd-disable-if="person.type == 'unknown'"                                              
                                              class="background-{{person.type}} {{list.slot}}"
                                              >
                                              <a href="#" ng-click="hiddenDiv = !hiddenDiv">{{person.name}}</a>
                                              <ul ng-if="person.type == 'signal'" ng-show="hiddenDiv">
                                                <li ng-repeat="net in person.nw">
                                                    <a href="#" ng-click="addnwsignal(net.id,person.id,list.slot)">{{net.name}}</a>
                                                </li>
                                              </ul>
                                              <input ng-if="list.slot == 'ecu_slot'" type="text" ng-model="" place-holder="Ecu feature name">                                              
                                          </li>
                                          <li class="dndPlaceholder">
                                              Drop any <strong>{{list.allowedTypes.join(' or ')}}</strong> here
                                          </li>
                                      </ul>                                      
                              </div>                              
                            </div>
                             <div class="feat_prop_save text-center">
                                <a href="#" ng-click="feature_result_cap()" class="btn btn-round btn-info">Save</a>
                                <a href="#" ng-click="feature_result()" class="btn btn-round btn-success">Submit</a>
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
                                  <div class="dropzone box-yellow">
                                    <!-- The dropzone also uses the list template -->
                                    <h3 class="text-center p-1">Assign Properties <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h3>
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
           
             <div id="modal-feature-list" class="modal modal-feature-list">
                <div class="modal-content search-model">                    
                    <h5 class="text-c-red m-b-10">Filter Search <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                    <ul>
                        <li ng-repeat="l in signaltags">
                            <input type="checkbox" ng-model='checkStatus' ng-click="includesignal(l.tag_id,l.signal_id,checkStatus)"/> 
                            &nbsp;<span>{{l.tagname}}</span>
                        </li>
                    </ul>
                </div>
                <div class="modal-content">                    
                    <h5 class="text-c-red m-b-10">Signals <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                    <!--<form class="form-inline mt-3">
                        <div class="form-group">
                            <input type="text" ng-model="search" class="form-control" placeholder="Search">
                        </div>
                    </form>-->
                    <ul>                       
                        <li ng-repeat="fil in signal_list|orderBy:sortKey:reverse|filter:tagFilter">
                            <a href="#" class="text-c-green" ng-click="add_signal_tab(cen.ip,cen.pri,fil.sid)">
                            <i class="icofont icofont-ui-add"></i></a>&nbsp;{{fil.listitem}}&nbsp;({{fil.description}})
                        </li>
                    </ul>                    
                </div>
            </div>
            
            <div class="col-lg-12 text-right">
                <a class="modal-trigger float-left text-c-green" style="font-weight:600" href="#modal-upload" style="text-decoration:underline;">
                    Import
                </a>
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
                
                <label for="status" style="vertical-align:middle">Status:</label>
                <label class="switch m-r-50"  style="vertical-align:middle">
                    <input type="checkbox" ng-model="data.status">
                    <span class="slider round"></span>
                 </label>
                
                <button ng-show="showSave == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createacbversion('save',0)" name="save">Save</button>
                <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createacbversion('submit',1)" name="submit">Submit</button>
                
            </div>  
            
            <!--<pre>list={{list}}</pre>-->
<%@include file="footer.jsp" %>
  <!--<script src="js/dirPagination.js"></script>-->
    <script>
//        var app = angular.module('angularTable', ['ui.bootstrap']);

        app.controller('RecordCtrl1',function($scope, $http, $window, $location, $element, $rootScope)
        {
            this.data1=[];
            this.data2=[]; 
            var notification_to;
            $scope.showSave =true;
            $scope.showSubmit =true;
            $scope.$on('notifyValue', function (event, args) {
                notification_to = args;
                $scope.createacbversion("submit",1);
            });
            $scope.pdbversion = [];
            $scope.ecu_list = [];
            $scope.signal_list = [];
            $scope.network = [];
            $scope.list = [];
            var features_group = [];
            var version_type;
            
            result_data_obj = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
            $scope.array_result = result_data_obj;
            
                        //Load Selected Feature Version Data
            $scope.LoadSelectedFeatureVersionData= function()
            {
                $http({
                    url : 'loadSelectedFeatureVersionData',
                    method : "POST",
                    data : {"id":$scope.data.featureversion.id}
                }).then(function (response, status, headers, config){
                    
                    var vm_result = [];
                    $scope.status_value = "";
                    $scope.vehicleresults = "";
                    result_data_obj = JSON.parse(response.data.result_data_obj.replace(/&quot;/g,'"'));
//                    $window.alert(JSON.stringify(result_data_obj));
                    var arr_res = [];
                   for(var i = 0; i < result_data_obj.length; i++)
                   {
                        arr_res.push({
                           //"id":data.id,
                            //"pdbversionname":data.pdbversionname,
                            "vid":result_data_obj[i].vid,
                            "vname":result_data_obj[i].vname,
                            "status":result_data_obj[i].status,
                            "flag":result_data_obj[i].flag
                        });
//                        status_value = data.status;  
//                       $scope.vehicleresults = response.data.maps_object.pdbversion[i];
//                       $window.alert(JSON.stringify(result_data_obj[i]));
                    }
                    $scope.data.vehiclename = arr_res[0];
                    $scope.arr_res = arr_res;
                    $scope.LoadIVNVersion();
                    $scope.LoadFeatures(result_data_obj[0].pdbid);
//                    $window.alert(JSON.stringify($scope.arr_res));
                });
            };
            $scope.LoadIVNVersion = function() {
                alert($scope.data.vehiclename.vid);
                $http({
                        url : 'LoadIVNVersion',
                        method : "POST",
                        data : {"vid":$scope.data.vehiclename.vid}
                    }).then(function (response, status, headers, config){
                        
                        var result_data = JSON.parse(response.data.result_data_obj.replace(/&quot;/g,'"'));
                        $scope.data.vername = result_data[0];
                        $scope.records = result_data;
                        if ($scope.data.vername) {
                            $scope.create_type = true;
//                            alert($scope.create_type);
                        }
//                        alert(JSON.stringify($scope.records)+" "+$scope.data.vername);
                });
            };
            $scope.LoadFeatures = function(pdbid) {
                alert("kjfdhskfh "+pdbid);
                $http({
                    url : 'LoadFeatures',
                    method : "POST",
                    data : {"pdbid":pdbid}
                }).then(function (response, status, headers, config){
                    
                    if (response.data.maps_string.success) {
                        alert(JSON.stringify(response.data.result_data));
                        $scope.features = response.data.result_data;
                    } else {
                        alert(response.data.maps_string.error);
                    }
                });
            };
            
//            $http.get("getPdbVersionFromFeatureVersion.action")
//                .then(function (response, status, headers, config) {
//                    
//                    if (response.data.maps_string.success) {
//                        
//                        var ids = {};
//
//                        $scope.pdbversion = response.data.result_data.filter(function(v) {
//                          var ind = v.name + '_' + v.key;
//                          if (!ids[ind]) {
//                            ids[ind] = true;
//                            return true;
//                          }
//                          return false;
//                        });
////                        $scope.pdbversion = response.data.result_data;
//                        $window.alert(JSON.stringify($scope.pdbversion));
//                    } else {                        
//                        $window.alert(response.data.maps_string.error);
//                    }
//                });
                    
//            $scope.list.features_group = [];
            
//            $scope.Confirm = function() {
//            alertModalInstance = $uibModal.open({
//              animation: $scope.animationsEnabled,
//              templateUrl: '<div></div>',
//              scope: $scope
//            });}
            $scope.hiddenDiv = false;
            $scope.showDiv = function () 
            {
                $scope.hiddenDiv = !$scope.hiddenDiv;
            };            
            $scope.fea = [];
            $scope.result = [];
            $scope.ipsignal = [];
            $scope.opsignal = [];
            
            $scope.assignstart = function(a)
            {
                $scope.fea.push({'fid':a});
                
            }
            $scope.addnwsignal = function(nid,sid,type)
            {
                if(type=='ip')
                {
                        const index = $scope.ipsignal.findIndex((e) => e.sid === sid);
                        if (index === -1) 
                        {
                           $scope.ipsignal.push({sid:sid,nw:nid});
                        }
                        else 
                        {
                            $scope.ipsignal[index].nw = nid;
                        }
                         $("li.ip > ul").addClass("ng-hide");
                        alert(JSON.stringify($scope.ipsignal));  
                }
                if(type=='op')
                {
                   const index = $scope.opsignal.findIndex((e) => e.sid === sid);
                    if (index === -1) 
                    {
                       $scope.opsignal.push({sid:sid,nw:nid});
                    }
                    else 
                    {
                        $scope.opsignal[index].nw = nid;
                    }
                     $("li.op > ul").addClass("ng-hide");
                   alert(JSON.stringify($scope.opsignal));
                }               
            }
            $scope.feature_result_cap = function()
            {
              $scope.result.push({'feature':$scope.fea,'ipsignal':$scope.ipsignal,'opsignal':$scope.opsignal});
              alert(JSON.stringify($scope.result));
//                alert(JSON.stringify($scope.models.dropzones.B));
            }
            $scope.feature_result = function()
            {
                
            }
            $scope.modals = [
                        { vmm_id:'1',modelname: 'm1'},
                        { vmm_id:'2',modelname: 'm2'},
                        { vmm_id:'3',modelname: 'm3'},
                        { vmm_id:'4',modelname: 'm4'}
                    ];              
            $scope.features = [
                        { fid:'1',featurename: 'feature1',status:"Y,O,Y,N",touch:'No'},
                        { fid:'2',featurename: 'feature2',status:'O,N,Y,N',touch:'No'},
                        { fid:'3',featurename: 'feature3',status:'Y,Y,O,N',touch:'No'},
                        { fid:'4',featurename: 'feature4',status:'Y,Y,N,O',touch:'No'}
                    ];    
//            $scope.ecu_list = [ 
//                { eid:'1',listitem:'ecu 1',description:'description 1'},
//                { eid:'2',listitem:'ecu 2',description:'description 2'},
//                { eid:'3',listitem:'ecu 3',description:'description 3'},
//                { eid:'4',listitem:'ecu 4',description:'description 4'}
//            ];
//            $scope.signal_list = 
//            [
//                { sid:'1',listitem:'signal 1',description:'description 1'},
//                { sid:'2',listitem:'signal 2',description:'description 2'},
//                { sid:'3',listitem:'signal 3',description:'description 3'},
//                { sid:'4',listitem:'signal 4',description:'description 4'}
//            ];
//            $scope.network = [
//                { id:'1',listitem:'CAN1',ntype:'can'},
//                { id:'2',listitem:'CAN2',ntype:'can'},
//                { id:'3',listitem:'CAN3',ntype:'can'},
//                { id:'4',listitem:'CAN4',ntype:'can'},
//                { id:'1',listitem:'LIN1',ntype:'lin'},
//                { id:'2',listitem:'LIN2',ntype:'lin'},
//                { id:'3',listitem:'LIN3',ntype:'lin'},
//                { id:'4',listitem:'LIN4',ntype:'lin'},
//                { id:'1',listitem:'H/W1',ntype:'hardware'},
//                { id:'2',listitem:'H/W2',ntype:'hardware'},
//               { id:'3',listitem:'H/W3',ntype:'hardware'},
//                { id:'4',listitem:'H/W4',ntype:'hardware'}
//              ];
            $scope.signaltags = [];            
             
            $scope.LoadVehicleData = function() {
                alert(JSON.stringify($scope.data.pdbversion));
            }

    $scope.dropCallback = function(index, item, external, type) 
    {
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
                          label: "Signal",
                          slot:"signal_slot",
                          allowedTypes: ['signal'],
                          max: 10,
                          version: [
                              {id:1,name: "AUTO_SWITCH", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:2,name: "Solar temperature", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:3,name: "Ambient Temperature", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:4,name: "AC_Switch", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:5,name: "Drive Mode", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:6,name: "IGN status", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:7,name: "Current_Gear_MT", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:8,name: "Vehicle_Speed_ESC", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:9,name: "Compressor control", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]},
                              {id:10,name: "DRV_SET_TEMP", type: "signal",nw:[{id:1,name: "can"},{id:2,name: "lin"},{id:3,name: "h/w"}]}
                          ]
//                          version:[]
                      }, 
                      {
                          label: "i/p Signal Slot",
                          slot:"ip",
                          allowedTypes: ['signal'],
                          max: 3,
                          version: []
                      },
                      {
                          label: "ECU Slot",
                          slot:"ecu_slot",
                          allowedTypes: ['ecu'],
                          max: 2,
                          version: []
//                          version:[]
                      },
                      {
                          label: "o/p Signal Slot",
                          slot:"op",
                          allowedTypes: ['signal'],
                          max: 3,
                          version: []
                      },
                      {
                          label: "ECU",
                          slot:"ecu_list",
                          allowedTypes: ['ecu'],
                          max: 4,
                          version: [
                              {name: "HVAC Systems", type: "ecu"},
                              {name: "DZATC", type: "ecu"},
                              {name: "ETC2", type: "ecu"}
                          ]
//                          version:[]
                      }                      
                  ]
                }                
              };
//              alert(JSON.stringify($scope.models.dropzones.B[0].version));

              $scope.$watch('models.dropzones', function(model) {
                $scope.modelAsJson = angular.toJson(model, true);
              }, true);
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
                        alert("Successfully Imported");
                        $window.open("acb_listing.action","_self");
                    }, function error(response) {
                        $(".loader-block").hide();
                        alert("Error");
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

                   var uploadUrl = "acbImport";
                   fileUpload.uploadFileToUrl(file, uploadUrl);
                   //$window.open("acb_listing.action","_self");
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