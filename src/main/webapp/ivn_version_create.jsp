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
                                                        <h4>IVN Engineer</h4>
                                                        <span>IVN Version Creation</span>    
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
                                                            <s:url action="ivn_engineer.action" var="aURL" />
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
                                                                <select ng-model="data.featureversion" ng-options="arr as arr.versionname for arr in array_result" ng-change="LoadSelectedFeatureVersionData()">
                                                                </select>
<!--                                                                <select ng-model="data.featureversion" ng-change="LoadSelectedVehicleVersionData()">
                                                                    <s:iterator value="vehicleversion_result" >
                                                                        <option value="<s:property value="id"/>">
                                                                            <s:property value="versionname"/>
                                                                        </option>
                                                                    </s:iterator>
                                                                </select>-->
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Vehicle:</label>
                                                                <select ng-model="data.vehiclename" ng-options="arr as arr.vname for arr in arr_res" ng-change="LoadIVNVersion()">
                                                                </select>
<!--                                                                <select ng-change="LoadVehicleModels(data.vehiclename)" ng-if="vehicle_list.length > 0" ng-model="data.vehiclename">
                                                                        <option value="{{veh.vehicle_id}}" ng-repeat="veh in vehicle_list">{{veh.vehiclename}}</option>                                                                    
                                                                </select>-->
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Version:</label>
                                                                <select ng-model="data.vername" ng-options="arr as arr.ivn_version for arr in records track by arr.ivn_version" >
                                                                </select>
<!--                                                                <select ng-change="LoadVehicleModels(data.vehiclename)" ng-if="vehicle_list.length > 0" ng-model="data.vehiclename">
                                                                        <option value="{{veh.vehicle_id}}" ng-repeat="veh in vehicle_list">{{veh.vehiclename}}</option>                                                                    
                                                                </select>-->
                                                            </div>
<!--                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">IVN version :</label>
                                                                <select ng-model="data.ivnversion" ng-change="LoadIVNPreviousVersion()">
                                                                    <s:iterator value="ivnversion_result" >
                                                                        <option value="<s:property value="id"/>">
                                                                            <s:property value="ivn_versionname"/>
                                                                        </option>
                                                                    </s:iterator>
                                                                </select>
                                                            </div>-->
                                                             
                                                            <a class="feature_add sig_add modal-trigger" href="#modal-signal-list">
                                                                <i class="icofont icofont-drag text-c-green"></i>
                                                                Signal List
                                                            </a>
                                                             <a class="feature_add ecu_add modal-trigger" href="#modal-ecu-list">
                                                                <i class="icofont icofont-drag1 text-c-green"></i>
                                                                Ecu List
                                                            </a>
                                                            <a class="feature_add_tip waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-product-form" ng-click="showCreateForm()">Add</a>
                                                            
                                                        </div>   
                                                        <div class="col-lg-12">
                                                            <div ng-tabs class="tabbable">   
                                                                <ul class="nav nav-tabs">
                                                                    <li ng-tab-head ="active" ng-click="tabstep1()">
                                                                        <a href="#" >Signals</a>
                                                                    </li>
                                                                    <li ng-tab-head="" ng-click="tabstep2()">
                                                                        <a href="#" >ECU</a>
                                                                    </li>
                                                                    
                                                                </ul>
                                                                <div class="tab-content" style="overflow: hidden">
                                                                    
                                                                    <div ng-tab-body="animated bounceInLeft" class="tab-pane ">
                                                                        <h5 class="m-t-10">Select or Add Signal</h5>
                                                                        <div id="accordion" role="tablist" aria-multiselectable="true">
                                                                                            
                                                                            <div class="accordion-panel" ng-repeat="s in signal">                                                         
                                                                                <div class="accordion-heading" role="tab" id="heading{{s.sid}}">
                                                                                    <h3 class="card-title accordion-title">
                                                                                    <a class="accordion-msg" data-toggle="collapse"
                                                                                       data-parent="#accordion" href="#collapse{{s.sid}}"
                                                                                       aria-expanded="true" aria-controls="collapse{{s.sid}}">
                                                                                        {{s.listitem}}
                                                                                    </a>
                                                                                    <a href="#" ng-click="removeSignalRow(s.sid)" class="removeSignalRow"><i class="icofont icofont-ui-close text-c-red"></i></a>    
                                                                                </h3>
                                                                                </div>
                                                                                <div id="collapse{{s.sid}}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading{{s.sid}}">
                                                                                    <div class="accordion-content accordion-desc">
                                                                                        <div ng-if="s.description">
                                                                                            <label>Description :</label>
                                                                                                {{s.description}}
                                                                                        </div>

                                                                                        <div ng-if="s.can">
                                                                                            <label>CAN :</label>
                                                                                                {{s.can}}
                                                                                        </div>

                                                                                        <div ng-if="s.lin">
                                                                                            <label>LIN :</label>
                                                                                                {{s.lin}}
                                                                                        </div>

                                                                                        <div ng-if="s.lin">
                                                                                            <label>Hardware :</label>
                                                                                                {{s.hardware}}
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                        
                                                                    </div>
                                                                    <div ng-tab-body="animated bounceInLeft" class="tab-pane">
                                                                           <h5 class="m-t-10">Select or Add ECU</h5>
                                                                          <div ng-repeat="e in ecu">
                                                                                <a href="#" ng-click="removeEcuRow(e.eid)" class="removeEcuRow"><i class="icofont icofont-ui-close text-c-red"></i></a>
<!--                                                                                <div class="border-checkbox-section check_pan">                                                                                    
                                                                                    <div class="border-checkbox-group border-checkbox-group-success">
                                                                                        <input class="border-checkbox" type="checkbox" id="checkbox_eu_{{e.eid}}">
                                                                                        <label class="border-checkbox-label" for="checkbox_eu_{{e.eid}}"></label>
                                                                                    </div>
                                                                                </div>-->
                                                                                <label>{{e.listitem}} ({{e.description}})</label>           
                                                                            </div>                                                                    
                                                                    </div>
                                                                    
                                                                </div><!--tab-content-->
                                                            </div><!--tabbable-->
                                                        </div>  <!--col-lg-12-->                                             
                                                    </div>
                                                </div>
                                            <!--</div>-->
                                            <!-- Marketing End -->
            <!--<form class=""  name="myForm">-->
                <!-- modal for for creating new product -->
                <div id="modal-product-form" class="modal">
                    <div class="modal-content">
                        <h5 class="text-c-red m-b-25">Add Network / Signal / ECU
                            <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" >
                                <i class="icofont icofont-ui-close"></i>
                            </a>
                        </h5>
                       
                            <div class="form-group">
                                <!--<label for="name">Domain</label>-->
                                <select ng-model="data.network" ng-change="SelectNetwork()" class="col-lg-12">
                                    <option value="" selected>Select your Network</option>
                                    <option value="can">CAN</option>
                                    <option value="lin">LIN</option>
                                    <option value="hardware">H/W</option>
                                    <option value="signals">Signal</option>
                                    <option value="ecu">ECU</option>
                                </select>
                            </div>
                                <div class="" ng-if="data.network !== 'signals'">
                                     <div ng-repeat="data in Demo.data">              
                                        <div class="form-group">
                                        <!--<label for="name">Feature</label>-->
                                        <input ng-model="data.name" type="text" class="validate col-lg-12" id="form-name" placeholder="Name"/>
                                        </div>
                                        <div class="form-group">
                                        <textarea ng-model="data.description" type="text" class="validate materialize-textarea  col-lg-12" placeholder="Description"></textarea>
                                        <!--<label for="description">Description</label>-->
                                        </div>
                                         <p class="text-right">
                                         <a href="" ng-click="Demo.data.splice($index,1)">
                                             <i class="icofont icofont-ui-close text-c-red "></i>
                                         </a>
                                         </p>
                                    </div>

                                    <p class="text-right">
                                        <a href="" ng-click="Demo.data[Demo.data.length] = {}">
                                             <i class="icofont icofont-ui-add text-c-green"></i>
                                         </a>
                                    </p>
                            </div>
                            <div class="signal_attr row" ng-if="data.network === 'signals'">              
                                
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.name" type="text" class="validate" id="form-name" placeholder="Name"/>
                                </div>
                                 <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.alias" type="text" class="validate" id="form-name" placeholder="alias"/>
                                </div> 
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.length" type="text" class="validate" id="form-name" placeholder="Length"/>
                                </div>
                                 
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.byteorder" type="text" class="validate" id="form-name" placeholder="Byte Order"/>
                                </div>
                                 
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.unit" type="text" class="validate" id="form-name" placeholder="Unit"/>
                                </div>
                                
                                <div class="form-group col-lg-6">
                                    <select ng-model="data.valuetype" ng-change="Selectvalue()">
                                        <option value="" selected>Select your Value</option>
                                        <option value="unsign">Unsigned</option>
                                        <option value="sign">Signed</option>
                                    </select>
                                </div>
                                
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.initvalue" type="text" class="validate" id="form-name" placeholder="Init Value"/>
                                </div>
                                 
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.factor" type="text" class="validate" id="form-name" placeholder="factor"/>
                                </div>
                                 
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.offset" type="text" class="validate" id="form-name" placeholder="Offset"/>
                                </div>
                                 
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.minimum" type="text" class="validate" id="form-name" placeholder="Minimum"/>
                                </div> 
                                
                                <div class="form-group col-lg-6">
                                    <!--<label for="name">Feature</label>-->
                                    <input ng-model="data.maximum" type="text" class="validate" id="form-name" placeholder="Maximum"/>
                                </div>
                                 
                                 <div class="form-group col-lg-6">
                                    <select ng-model="data.valuetable" ng-change="">
                                        <option value="" selected>Select your Value Table</option>
                                        <option value="valuetable_1">valuetable_1</option>
                                        <option value="valuetable_2">valuetable_2</option>
                                    </select>
                                </div> 
                                
                                <div class="form-group col-lg-6">                                    
<!--                                    <input ng-model="data.maximum" type="checkbox" class="validate"/>-->
                                    <input type="checkbox" class="validate"/>
                                    <label for="name">Automatic Min-Max Calculation</label>
                                </div>
                                 
                                <div class="form-group col-lg-12">
                                    <textarea ng-model="data.description" type="text" class="validate materialize-textarea  col-lg-12" placeholder="Description"></textarea>
                                    <!--<label for="description">Description</label>-->
                                </div>
                                <div class="form-group col-lg-12">
                                    <tags-input ng-model="data.tags"  use-strings="true"></tags-input>
                                </div>
                                
                                <div class="form-group col-lg-12 pdb_sig_assign">
                                    <label>CAN </label> : 
                                    <div ng-repeat="c in cans">
                                        <label>{{c.listitem}}</label>
                                        <div class="border-checkbox-section check_pan">                                                                                    
                                            <div class="border-checkbox-group border-checkbox-group-success">
                                                <input class="border-checkbox" type="checkbox" id="checkbox_ca_{{s.sid}}_{{c.cid}}" ng-click="checkboxvalue('can',c.cid)">
                                                <label class="border-checkbox-label" for="checkbox_ca_{{s.sid}}_{{c.cid}}"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group col-lg-12 pdb_sig_assign">
                                    <label>LIN </label> : 
                                    <div ng-repeat="l in lin">
                                        <label>{{l.listitem}}</label>
                                        <div class="border-checkbox-section check_pan">                                                                                    
                                            <div class="border-checkbox-group border-checkbox-group-success">
                                                <input class="border-checkbox" type="checkbox" id="checkbox_li_{{s.sid}}_{{l.lid}}" ng-click="checkboxvalue('lin',l.lid)">
                                                <label class="border-checkbox-label" for="checkbox_li_{{s.sid}}_{{l.lid}}"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group col-lg-12 pdb_sig_assign">
                                    <label>H/W </label> : 
                                    <div ng-repeat="h in hw">
                                        <label>{{h.listitem}}</label>
                                        <div class="border-checkbox-section check_pan">                                                                                    
                                            <div class="border-checkbox-group border-checkbox-group-success">
                                                <input class="border-checkbox" type="checkbox" id="checkbox_hw_{{s.sid}}_{{h.hid}}" ng-click="checkboxvalue('hardware',h.hid)">
                                                <label class="border-checkbox-label" for="checkbox_hw_{{s.sid}}_{{h.hid}}"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="input-field text-right">
                                <!--<a id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em float-right" ng-click="createfeature()">Add</a>-->
                                <button id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em float-right" ng-click="create_ivn_required_attributes()" ng-mousedown='doSubmit=true' name="add">Add</button>
                            </div>
                            
                    </div>
                </div>
                <!-- floating button for creating product -->
            <!--</form>-->
            
            <div id="modal-signal-list" class="modal modal-feature-list">
                <div class="modal-content">
                    <h5 class="text-c-red m-b-10">Signals <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                    
                    <ul>
                        <li ng-repeat="fil in signal_list">
                            <a href="#" class="text-c-green" ng-click="add_signal_tab(fil.sid)">
                                <i class="icofont icofont-ui-add"></i>
                            </a>&nbsp;{{fil.listitem}}&nbsp;({{fil.description}})
                        </li>
                    </ul>
                    
                </div>
            </div>
           
             <div id="modal-ecu-list" class="modal modal-feature-list">
                <div class="modal-content">
                    
                    <h5 class="text-c-red m-b-10">ECU <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                    
                    <ul>
                        <li ng-repeat="fil in ecu_list">
                            <a href="#" class="text-c-green" ng-click="add_ecu_tab(fil.eid)">
                                <i class="icofont icofont-ui-add"></i>
                            </a>&nbsp;{{fil.listitem}}&nbsp;({{fil.description}})
                        </li>
                    </ul>
                    
                </div>
            </div>
            
            <div class="col-lg-12 text-right">
                <a class="modal-trigger float-left text-c-green" style="font-weight:600" href="#modal-upload" style="text-decoration:underline;" ng-click="assignstart(record.fid)">
                    Import
                </a>
                <div id="modal-upload" class="modal">
                    <div class="modal-content">
                        <h5 class="text-c-red m-b-10"><a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
<!--                        <div class="float-left">
                            <input type="file" name="userImport" label="User File" />
                             <button  class="btn btn-primary">Import</button>
                        </div>-->
                        <div ng-controller = "fileCtrl" class="text-center">
                            <input type = "file" name="userImport" file-model = "myFile" accept=".csv"/>
                            </br></br>

                            <button class="btn btn-primary" ng-click = "uploadFile()">Import Signal</button>
                            <button class="btn btn-primary" ng-click = "uploadIVN()">Import IVN Version</button>
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
                <a class="feature_add_tip modal-trigger btn-floating btn-primary" ng-show="showProceed == true" style="padding:10px" href="#modal-comment" ng-click="showCreateForm()">Proceed</a>
                <div id="modal-comment" class="modal">
                     <div class="modal-content text-left">

                         <h5 class="text-c-red m-b-10">Comment <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                         <input type="text" class="col-md-12 m-b-10" ng-model="data.version_name" placeholder="Version Name" />
                         <textarea class="col-md-12 m-b-10" ng-model="data.pdb_manual_comment" placeholder="Your Comment" ></textarea>
                         <div ng-if="create_type == true">
                             <input type="radio" ng-click="" ng-model="data.version_change" value="major" class="radio_button">Major
                             &nbsp;<input type="radio" ng-click="" ng-model="data.version_change" value="minor" class="radio_button">Minor
                         </div>
                         <div class="text-right">
                             <button ng-show="showSave == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createIvnVersion('save')" name="save">Save</button>
                             <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createIvnVersion('submit')" name="submit">Submit</button>
                         </div>
                     </div>
                 </div>
                
            </div>  
            
            <!--<pre>list={{list}}</pre>-->
<%@include file="footer.jsp" %>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>-->
  <!--<script src="js/dirPagination.js"></script>-->
    <script>
//        var app = angular.module('angularTable', ['angularUtils.directives.dirPagination']);

        app.controller('RecordCtrl1',function($scope, $http, $window, $location)
        {
            this.data=[];
            var notification_to;
            $scope.features = [];
            $scope.ivn_version = "";
//            $scope.list = [];
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
            $scope.$on('notifyValue', function (event, args) {
                notification_to = args;
                $scope.createIVNVersionAjax("submit");
            });
            
            $scope.list = {};
            
            result_data_obj = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
            $scope.array_result = result_data_obj;
            
            network_list = JSON.parse("<s:property value="network_list_obj"/>".replace(/&quot;/g,'"'));
            $scope.cans = network_list.can_list;
            $scope.lin = network_list.lin_list;
            $scope.hw = network_list.hardware_list;
            
            $scope.signal = [];
              
            $scope.ecu = [];
            ecu_list = JSON.parse("<s:property value="eculist_result_obj"/>".replace(/&quot;/g,'"'));
            $scope.ecu_list = ecu_list;

            signal_list = JSON.parse("<s:property value="signallist_result_obj"/>".replace(/&quot;/g,'"'));
            $scope.signal_list = signal_list;
//            alert(JSON.stringify($scope.signal_list));
//            alert("feature_list  "+JSON.stringify(result_data_obj)+"network_list  "+JSON.stringify(network_list)+" eculist_list  "+JSON.stringify(ecu_list)+" signal_list  "+JSON.stringify(signal_list));

            $scope.sort = function(keyname)
            {
                $scope.sortKey = keyname;   //set the sortKey to the param passed
                $scope.reverse = !$scope.reverse; //if true make it false and vice versa
            }
            
            $scope.add_signal_tab = function(sid)
            {
		var index = -1;		
		var comArr = eval( $scope.signal_list );
		for( var i = 0; i < comArr.length; i++ ) 
                {
//                    alert(sid+" and "+comArr[i].sid);
                    if( comArr[i].sid === sid ) 
                    {
                        index = i;
                        break;
                    }
		}
		if( index === -1 ) 
                {
			alert( "Something gone wrong" );
		}
//                angular.element( document.querySelector( '.tab-pane' ) ).css('display','none');
//                var idr = '#signals';
//                var myEl = angular.element( document.querySelector( idr ) );
//                myEl.css('display','block');
                $scope.signal.push({sid:comArr[index].sid,listitem:comArr[index].listitem,description: comArr[index].description})
                if($scope.list.signal == undefined){
                    $scope.list.signal = [];
                }
                $scope.list.signal.push(comArr[index].sid);
                $scope.signal_list.splice( index, 1 );
//                alert(JSON.stringify($scope.list));
            };
            
            $scope.removeSignalRow = function(sid)
            {
		var index = -1;		
		var comArr = eval( $scope.signal );
		for( var i = 0; i < comArr.length; i++ ) 
                {
                    if( comArr[i].sid === sid ) 
                    {
                        index = i;
                        break;
                    }
		}
		if( index === -1 ) 
                {
			alert( "Something gone wrong" );
		} 
                $scope.signal_list.push({sid:comArr[index].sid,listitem:comArr[index].listitem,description: comArr[index].description})
                $scope.signal.splice( index, 1 );
                $scope.list.signal.splice(index,1);
//                alert(JSON.stringify($scope.list));
            };
            
            $scope.add_ecu_tab = function(eid)
            {
		var index = -1;		
		var comArr = eval( $scope.ecu_list );
		for( var i = 0; i < comArr.length; i++ ) 
                {
                    if( comArr[i].eid === eid ) 
                    {
                        index = i;
                        break;
                    }
		}
		if( index === -1 ) 
                {
			alert( "Something gone wrong" );
		}
//                angular.element( document.querySelector( '.tab-pane' ) ).css('display','none');
//                var idr = '#ecu';
//                var myEl = angular.element( document.querySelector( idr ) );
//                myEl.css('display','block');

                $scope.ecu.push({eid:comArr[index].eid,listitem:comArr[index].listitem,description: comArr[index].description})
		if($scope.list.ecu == undefined){
                    $scope.list.ecu = [];
                }
                $scope.list.ecu.push(comArr[index].eid);
                $scope.ecu_list.splice( index, 1 );
//                alert(JSON.stringify($scope.list));
                
            };
            
            $scope.removeEcuRow = function(eid)
            {
		var index = -1;		
		var comArr = eval( $scope.ecu);
		for( var i = 0; i < comArr.length; i++ ) 
                {
                    if( comArr[i].eid === eid ) 
                    {
                        index = i;
                        break;
                    }
		}
		if( index === -1 ) 
                {
			alert( "Something gone wrong" );
		}
                $scope.ecu_list.push({eid:comArr[index].eid,listitem:comArr[index].listitem,description: comArr[index].description})
                $scope.ecu.splice( index, 1 );	
                $scope.list.ecu.splice(index,1);
//                alert(JSON.stringify($scope.list));
            };
            
            $scope.SelectNetwork = function()
            {
                Object.keys($scope.Demo.data).forEach(function(itm){
                    alert("!!!!!!!!!!!!"+itm);
                    if(itm != "network") delete $scope.Demo.data[itm];
                });
                $scope.Demo.data = [];
            };
            
            $scope.create_ivn_required_attributes = function (event) 
            {
                if (!$scope.doSubmit) 
                {
                    return;
                }
                $scope.doSubmit = false; 
                var ivn_attribute_data = {};
                ivn_attribute_data['network'] = $scope.data.network;
                if($scope.data.network == "signals")
                    ivn_attribute_data['ivn_attribute_data'] = $scope.data;
                else
                    ivn_attribute_data['ivn_attribute_data'] = $scope.Demo.data;
                if(($scope.data.network != "signals" && $scope.Demo.data.length > 0 && $scope.Demo.data[0].name != undefined  && $scope.Demo.data[0].description != undefined)||
                        ($scope.data.network == "signals" && $scope.data.name != undefined  && $scope.data.description != undefined && $scope.data.alias != undefined))
                {
                    alert(JSON.stringify($scope.data)+"   "+JSON.stringify(ivn_attribute_data));
                    $http({
                        url : 'create_ivn_required_attributes',
                        method : "POST",
                        data : ivn_attribute_data
                    })
                   .then(function (data, status, headers, config)
                    {
                        result_data_obj = JSON.parse(data.data.result_data_obj.replace(/&quot;/g,'"'));
                        alert(data.data.maps_object.status+"   "+result_data_obj);
//                        alert(JSON.stringify(data.data.result_data));
                        angular.forEach(result_data_obj, function(value, key) {
                            if($scope.data.network == "can")
                                $scope.cans.push(value);
                            else if($scope.data.network == "lin")
                                $scope.lin.push(value);
                            else if($scope.data.network == "hardware")
                                $scope.hw.push(value);
                            else if($scope.data.network == "ecu"){
                                if($scope.list.ecu == undefined){
                                    $scope.list.ecu = [];
                                }
                                $scope.list.ecu.push(value.eid);
                                $scope.ecu.push(value);
                                $scope.ecu_list = $scope.ecu;
                            }
                            else if($scope.data.network == "signals")
                            {
                                if($scope.list.signal == undefined)
                                {
                                    $scope.list.signal = [];
                                }
                                $scope.list.signal.push(value.sid);
                                $scope.signal.push(value);
                                $scope.signal_list = $scope.signal;
                            }
                        });
                        alert(JSON.stringify($scope.signal));
                    });
                    can = [];
                    lin = [];
                    hardware = [];
                    $scope.data.network="";
                    $scope.Demo.data=[];
                    $scope.data=[];
                    location.reload();
                    $('#modal-product-form').closeModal();
                } else {
                    if($scope.data.network == "signals")
                        alert("Please fill the name and description and alias");
                    else
                        alert("Please fill all the fields");
                }
            }
            
            var can = [];
            var lin = [];
            var hardware = [];
            $scope.checkboxvalue = function(network_type,network_id)
            {
                network_type = eval(network_type);
                if(network_type.indexOf(network_id) !== -1)
                    network_type.splice(can.indexOf(network_id), 1);     
                else
                    network_type.push(network_id);
                $scope.data.can = can.toString();
                $scope.data.lin = lin.toString();
                $scope.data.hardware = hardware.toString();                              
            };
            
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
            $scope.createIVNVersionAjax = function (event){
                var status = $scope.data.status;
                if(status == undefined || status == false)
                    notification_to = undefined;
                var data = {};
//                alert(JSON.stringify($scope.data));
                data['ivnversion'] = $scope.data;
                data['ivndata_list'] = $scope.list;
                data['button_type'] = event;
                data['notification_to'] = notification_to+"";
                alert(JSON.stringify(data));
                console.log(JSON.stringify(data));
                $http({
                    url : 'createivnversion',
                    method : "POST",
                    data : data,
                })
                .then(function (response, status, headers, config){
//                    $scope.vercompare_results = {"removed_features":"(d1) feature3, (d1) feature5", 
//                                                 "added_features":"(d1) feature4", 
//                                                 "removed_models":"m2,m4", "added_models":"m3", 
//                                                 "previous_version":"1.0", "current_version":"1.1"
//                                                };
                    alert(response.data.maps_object.status);
                    var vercompare_res = response.data.maps_object.ivn_previous_data_result;
                    if(vercompare_res != undefined){
                        
                          $scope.vercompare_results = response.data.maps_object.ivn_previous_data_result;
                          alert(JSON.stringify($scope.vercompare_results));    
                    }
                    else{
                          alert("No any previous version found to compare");
                    }
                    $('#modal-comment').closeModal();
                    if(response.data.maps_string.status_code == "1")
                        $window.open("ivn_version_listing.action","_self");
                });
            };
            
            $scope.createIvnVersion = function (event)
            {
                var status = $scope.data.status;
                if(status == undefined)
                    status = false;
                if (!$scope.doSubmit)
                    return;
                $scope.doSubmit = false;         
//                alert(event);
//                $scope.list.push(this.text);
//                alert(JSON.stringify($scope.list));
                list_count = Object.keys($scope.list).length;
//                alert(JSON.stringify($scope.data)+" "+JSON.stringify($scope.list));
                
                if(list_count > 0 && $scope.data.featureversion != undefined && $scope.data.vehiclename != undefined && $scope.data.status != undefined
                         && $scope.list.signal != undefined && $scope.list.ecu != undefined) {
                     
                    if($scope.list.signal.length > 0 && $scope.list.ecu.length > 0) {
                        if(status && event === "submit")
                            $(".notifyPopup").click();
                        else
                            $scope.createIVNVersionAjax(event);
                    } else{
                        alert("Please fill all the details to create IVN version");
                    }
                } else{
                    alert("Please fill all the details to create IVN version");
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
            this.uploadFileToUrl = function(file, uploadUrl,mode){
               var fd = new FormData();
               fd.append('file', file);
            
               $http.post(uploadUrl, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
               }).then(function success(response) {
                        $(".loader-block").hide();
                        alert("Successfully Imported");
                        if(mode === 0)
                            $window.open("ivn_signals.action","_self");
                        else if(mode ===1)
                            $window.open("ivn_version_listing.action","_self");
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

                   var uploadUrl = "signalImport";
                   fileUpload.uploadFileToUrl(file, uploadUrl,0);
                   //$window.open("ivn_signals.action","_self");
                }
                else{
                   alert("Please upload CSV file for import");
                }
            };
            $scope.uploadIVN = function(){
                var file = $scope.myFile;
                if(file != undefined){
                    $(".loader-block").show();
    //                alert('hi');

                   //console.log('file is ' );
                   //console.dir(file);

                   var uploadUrl = "ivnImport";
                   fileUpload.uploadFileToUrl(file, uploadUrl,1);
                   //$window.open("ivn_version_listing.action","_self");
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
//            $scope.models = [
//                        { id:'1',mod: 'm1'},
//                        { id:'2',mod: 'm2'},
//                        { id:'3',mod: 'm3'},
//                        { id:'4',mod: 'm4'},
//                    ];              
//            $scope.cans = [
//                { cid:'1',listitem:'CAN1'},
//                { cid:'2',listitem:'CAN2'},
//                { cid:'3',listitem:'CAN3'},
//                { cid:'4',listitem:'CAN4'}
//              ];       
//            $scope.lin = [
//                { lid:'1',listitem:'LIN1'},
//                { lid:'2',listitem:'LIN2'},
//                { lid:'3',listitem:'LIN3'},
//                { lid:'4',listitem:'LIN4'}
//              ];           
//            $scope.hw = [
//                { hid:'1',listitem:'H/W1'},
//                { hid:'2',listitem:'H/W2'},
//                { hid:'3',listitem:'H/W3'},
//                { hid:'4',listitem:'H/W4'}
//              ]; 
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
    </script>   
</body>

</html>                                            