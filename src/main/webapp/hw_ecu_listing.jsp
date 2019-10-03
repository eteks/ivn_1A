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
                                                        <span>Network and ECU</span>    
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
                                                         
                                                        <div class="col-lg-12  p-t-30">
                                                                <div class="text-right">
                                                                    <a class="feature_add_tip waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-product-form" ng-click="showCreateForm()">Add</a>
                                                                </div>
                                                                <!-- Nav tabs -->
                                                                <ul class="nav nav-tabs  tabs" role="tablist">
                                                                    <li class="nav-item">
                                                                        <a class="nav-link selected" data-toggle="tab" href="#can" role="tab">CAN</a>
                                                                    </li>
                                                                    <li class="nav-item">
                                                                        <a class="nav-link" data-toggle="tab" href="#lin" role="tab">LIN</a>
                                                                    </li>
                                                                    <li class="nav-item">
                                                                        <a class="nav-link" data-toggle="tab" href="#hardware" role="tab">Hardware</a>
                                                                    </li>
                                                                    <li class="nav-item">
                                                                        <a class="nav-link" data-toggle="tab" href="#signals" role="tab">Signals</a>
                                                                    </li>
                                                                    <li class="nav-item">
                                                                        <a class="nav-link" data-toggle="tab" href="#ecu" role="tab">ECU</a>
                                                                    </li>
                                                                </ul>
                                                                <!-- Tab panes -->
                                                                <div class="tab-content tabs card-block">
                                                                    <div class="tab-pane" id="can" role="tabpanel">
                                                                        <form class="form-inline mt-3">
                                                                            <div class="form-group">
                                                                                <input type="text" ng-model="search" class="form-control" placeholder="Search">
                                                                            </div>
                                                                        </form>
                                                                        <table st-table="rowCollection" class="table table-striped">
                                                                                <thead>
                                                                                <tr>

                                                                                    <th class="text-center">CAN</th>                                                                                   

                                                                                </tr>
                                                                                </thead>

                                                                                <tbody>

                                                                                    <tr ng-repeat="record in cans">

                                                                                        <td class="text-center">
                                                                                            {{record.listitem}}
                                                                                        </td>

                                                                                        <td class="text-center" ng-repeat="i in models">                                                                                             
                                                                                            <div class="border-checkbox-section">                                                                                    
                                                                                                <div class="border-checkbox-group border-checkbox-group-success">
                                                                                                    <input class="border-checkbox" type="checkbox" id="checkbox_c_{{record.cid}}_{{i.id}}">
                                                                                                    <label class="border-checkbox-label" for="checkbox_c_{{record.cid}}_{{i.id}}"></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </td>
                                                                                        
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                    </div>
                                                                    
                                                                    <div class="tab-pane" id="lin" role="tabpanel">                                                                        
                                                                        <table st-table="rowCollection" class="table table-striped">
                                                                                <thead>
                                                                                <tr>

                                                                                    <th class="text-center">LIN</th>
                                                                                    <th class="text-center" ng-repeat="i in models">
                                                                                        {{i.mod}}
                                                                                    </th>

                                                                                </tr>
                                                                                </thead>

                                                                                <tbody>

                                                                                    <tr ng-repeat="record in lin">

                                                                                        <td class="text-center">
                                                                                            {{record.listitem}}
                                                                                        </td>

                                                                                        <td class="text-center" ng-repeat="i in models">                                                                                             
                                                                                            <div class="border-checkbox-section">                                                                                    
                                                                                                <div class="border-checkbox-group border-checkbox-group-success">
                                                                                                    <input class="border-checkbox" type="checkbox" id="checkbox_l_{{record.lid}}_{{i.id}}">
                                                                                                    <label class="border-checkbox-label" for="checkbox_l_{{record.lid}}_{{i.id}}"></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </td>
                                                                                        
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                    </div>
                                                                    <div class="tab-pane" id="hardware" role="tabpanel">
                                                                        <table st-table="rowCollection" class="table table-striped">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th class="text-center">Hardware</th>
                                                                                    <th class="text-center" ng-repeat="i in models">
                                                                                        {{i.mod}}
                                                                                    </th>
                                                                                </tr>
                                                                                </thead>

                                                                                <tbody>

                                                                                    <tr ng-repeat="record in hw">

                                                                                        <td class="text-center">
                                                                                            {{record.listitem}}
                                                                                        </td>

                                                                                        <td class="text-center" ng-repeat="i in models">                                                                                             
                                                                                            <div class="border-checkbox-section">                                                                                    
                                                                                                <div class="border-checkbox-group border-checkbox-group-success">
                                                                                                    <input class="border-checkbox" type="checkbox" id="checkbox_h_{{record.hid}}_{{i.id}}">
                                                                                                    <label class="border-checkbox-label" for="checkbox_h_{{record.hid}}_{{i.id}}"></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </td>
                                                                                        
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                    </div>
                                                                    <div class="tab-pane" id="signals" role="tabpanel">
                                                                        <div class="col-lg-12">
                                                                                <div class="card">
                                                                                    <div class="card-block accordion-block">
                                                                                        <div id="accordion" role="tablist" aria-multiselectable="true">
                                                                                            
                                                                                            <div class="accordion-panel" ng-repeat="s in signal_list">
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
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>                                                                                           
                                                                                            
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                    </div>
                                                                    <div class="tab-pane" id="ecu" role="tabpanel">
                                                                        
                                                                           
                                                                                <div ng-repeat="e in ecu_list">
                                                                                    <!--<a href="#" ng-click="removeEcuRow(e.eid)" class="removeEcuRow"><i class="icofont icofont-ui-close text-c-red"></i></a>-->
<!--                                                                                    <div class="border-checkbox-section check_pan">                                                                                    
                                                                                        <div class="border-checkbox-group border-checkbox-group-success">
                                                                                            <input class="border-checkbox" type="checkbox" id="checkbox_eu_{{e.eid}}">
                                                                                            <label class="border-checkbox-label" for="checkbox_eu_{{e.eid}}"></label>
                                                                                        </div>
                                                                                    </div>-->
                                                                                    <label>{{e.listitem}} ({{e.description}})</label>
                                                                                    
                                                                                </div>
                                                                        
                                                                    </div>
                                                                    
                                                                </div>
                                                            </div>                                               
                                                    </div>
                                                </div>
                                            </div>
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
                    alert("!!!!!!! "+itm);
                    if(itm !== "network") delete $scope.Demo.data[itm];
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
                
                alert(JSON.stringify($scope.data)+" "+JSON.stringify($scope.Demo.data));
                if($scope.data.network === "signals")
                    ivn_attribute_data['ivn_attribute_data'] = $scope.data;
                else
                    ivn_attribute_data['ivn_attribute_data'] = $scope.Demo.data;
//                alert(JSON.stringify(ivn_attribute_data)+" "+JSON.stringify($scope.Demo.data));
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
                }
                else
                {
                    if($scope.data.network == "signals")
                        alert("Please fill the name and description and alias");
                    else
                        alert("Please fill all the fields");
                }
            };
            
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
            
        });

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