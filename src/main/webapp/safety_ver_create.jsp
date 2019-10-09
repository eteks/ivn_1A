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
                                                            <a href="index.html"> <i class="icofont icofont-home"></i></a>
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
                                                         <div class="row p-t-30"> 
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Vehicle:</label>
                                                                <select ng-model="data.vehicle" ng-change="LoadPreviousVersion()">
                                                                    <option value="">Select Vehicle</option>
                                                                    <s:iterator value="vehicle_result" var="data">   
                                                                            <option value="<s:property value="id"/>"><s:property value="vehiclename"/></option>
                                                                    </s:iterator>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="pdbversion">PDB version :</label>
                                                                <select ng-model="data.pdbversion" ng-options="arr as arr.pdbversion_name for arr in array_result" ng-change="LoadVehicleModels()" disabled>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Safety version :</label>
                                                                <select ng-model="data.safetyversion" ng-options="arr as arr.safversion_name for arr in safarray_result" disabled>
                                                                </select>
                                                            </div>
                                                            <a class="waves-effect waves-light modal-trigger" href="#modal-product-form" ng-click="showCreateForm()">Add Combination</a>
                                                        </div>   
                                                            
                                                        <table st-table="rowCollection" class="table table-striped">
                                                                <thead>
                                                                <tr>
                                                                    
                                                                    <th class="">Safety</th>
                                                                    <th class="text-center" ng-repeat="i in records">
                                                                        {{i.modelname}}
                                                                    </th>

                                                                </tr>
                                                                </thead>
                                                                
                                                                <tbody>
                                                                <form ng-model="myform">    
                                                                    <tr dir-paginate="record in safety|orderBy:sortKey:reverse|filter:search|itemsPerPage:20">
                                                                        
                                                                       
                                                                        <td class="">
                                                                            <a href="#" ng-click="removeRow(record.qb_id)"><i class="icofont icofont-ui-close text-c-red"></i></a> {{record.qb_name}}
                                                                        </td>
                                                                        <td class="text-center" ng-repeat="i in records">                                                                             
                                                                              <label class="custom_radio mytooltip tooltip-effect-8">                                                                                
                                                                                <input type="radio" ng-click="radiovalue(record.qb_id,i.model_id,'y')" name="f{{record.qb_id}}_{{i.model_id}}" value="y" class="radio_button">
                                                                                <span class="checkmark c_b_g">                                                                                    
                                                                                </span>
                                                                                <span class="tooltip-content2">yes</span>
                                                                              </label>
                                                                              <label class="custom_radio mytooltip tooltip-effect-8">
                                                                                <input type="radio" ng-click="radiovalue(record.qb_id,i.model_id,'n')" name="f{{record.qb_id}}_{{i.model_id}}" value="n" class="radio_button">
                                                                                <span class="checkmark c_b_r"></span>
                                                                                <span class="tooltip-content2">no</span>
                                                                              </label>
                                                                              <label class="custom_radio mytooltip tooltip-effect-8">
                                                                                <input type="radio" ng-click="radiovalue(record.qb_id,i.model_id,'o')" name="f{{record.qb_id}}_{{i.model_id}}" value="o" class="radio_button">    
                                                                                <span class="checkmark c_b_b"></span>
                                                                                <span class="tooltip-content2">optional</span>
                                                                              </label>
                                                                        </td>
                                                                    </tr>
                                                                </form>
                                                                </tbody>
                                                            </table>
                                                        <dir-pagination-controls
                                                                max-size="20"
                                                                direction-links="true"
                                                                boundary-links="true">
                                                        </dir-pagination-controls>                                                        
                                                    </div>
                                                </div>
                                            </div>
                <div class="col-lg-12 text-right">
                 
                    <div id="modal-upload" class="modal">
                        <div class="modal-content">
                            <h5 class="text-c-red m-b-10"><a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                            <form class="float-left">
                                <input type="file" ng-model="" name=""/>
                                 <button ng-show="showSubmit == true" type="submit" class="btn btn-primary">Upload</button>
                            </form>

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
                             <textarea class="col-md-12 m-b-10" ng-model="data.safety_manual_comment"></textarea>
                             <div ng-if="create_type == true">
                                 <input type="radio" ng-click="" ng-model="data.version_change" value="major" class="radio_button">Major
                                 &nbsp;<input type="radio" ng-click="" ng-model="data.version_change" value="minor" class="radio_button">Minor
                             </div>
                             <div class="text-right">
                                 <button ng-show="showSave == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createsafetyversion('save')" name="save">Save</button>
                                 <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createsafetyversion('submit')" name="submit">Submit</button>
                             </div>
                         </div>
                     </div>
                </div>
                <!-- Marketing End -->
                        <div id="modal-product-form" class="modal">
                            <div class="modal-content">
                                <h5 class="text-c-red m-b-25">Feature Combination <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>                                
                                
                                           <div class="alert alert-info">
                                                <strong>Output</strong><br>
                                                <span ng-bind-html="output"></span>
                                            </div>
                                            <div class="col-md-12 col-lg-offset-1">
                                                <input type="text" id="combname" name="combname" placeholder="Name" class="col-md-12"/>
                                                <input type="hidden" id="combid"/>
                                                <input type="hidden" id="button_status"/>
                                            </div></br>
                                            <query-builder group="filter.group"></query-builder>
                                            <div class="col-md-12 col-lg-offset-1">
                                                <div id="builder-basic" style="display: block;"></div>
                                                <div class="btn-group float-right">                                                    
                                                        <button class="btn btn-primary parse-sql  float-right" data-target="import_export" data-stmt="false" id="btn-get" data-ctype="safety">Submit</button>                                                        
                                                </div>
                                            </div>
                                
                            </div>
                        </div>
             
                        <%@include file="footer.jsp" %>
                        <!--<script src="js/lib/materialize.min.js"></script>-->
                        <!--<script src="js/dirPagination.js"></script>-->
                        <script type="text/ng-template" id="/queryBuilderDirective.html">
                        <div class="alert alert-warning alert-group">
                            <div class="form-inline">
                                <select ng-options="o.name as o.name for o in operators" ng-model="group.operator" class="form-control input-sm"></select>
                                <button style="margin-left: 5px" ng-click="addCondition()" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Add Condition</button>
                                <button style="margin-left: 5px" ng-click="addGroup()" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Add Group</button>
                                <button style="margin-left: 5px" ng-click="removeGroup()" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> Remove Group</button>
                            </div>
                            <div class="group-conditions">
                                <div ng-repeat="rule in group.rules | orderBy:'index'" class="condition">
                                    <div ng-switch="rule.hasOwnProperty('group')">
                                        <div ng-switch-when="true">
                                            <query-builder group="rule.group"></query-builder>
                                        </div>
                                        <div ng-switch-default="ng-switch-default">
                                            <div class="form-inline">
                                                <select ng-options="t.name as t.name for t in fields" ng-model="rule.field" class="form-control input-sm"></select>
                                                <select style="margin-left: 5px" ng-options="c.name as c.name for c in conditions" ng-model="rule.condition" class="form-control input-sm"></select>
                                                <!-- <input style="margin-left: 5px" type="text" ng-model="rule.data" class="form-control input-sm"/> -->
                                                <button style="margin-left: 5px" ng-click="removeCondition($index)" class="btn btn-sm btn-danger">X</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </script>
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

         var data = '{"group": {"operator": "AND","rules": []}}';
         var action;
         $scope.records = [];
//         alert("yes");
         $scope.safety= JSON.parse("<s:property value="maps_object.safcomb_list_res"/>".replace(/&quot;/g,'"'));
//             $scope.legislation = [{"lid":"1","leg":"Legislation1","yes":"power window,RSC,f4","no":"AEB","opt":"f2"},
//                                    {"lid":"2","leg":"Legislation2","yes":"f3","no":"f4,f2","opt":"f1"}];
//        alert(JSON.stringify($scope.safety));
        function htmlEntities(str) {
            return String(str).replace(/</g, '&lt;').replace(/>/g, '&gt;');
        }

        function computed(group) {
            if (!group) return "";
            for (var str = "(", i = 0; i < group.rules.length; i++) {
                i > 0 && (str += " <strong>" + group.operator + "</strong> ");
                str += group.rules[i].group ?
                    computed(group.rules[i].group) :
                    group.rules[i].field + " " + htmlEntities(group.rules[i].condition) + " " + group.rules[i].data;
            }

            return str + ")";
        }

        $scope.json = null;

        $scope.filter = JSON.parse(data);

        $scope.$watch('filter', function (newValue) {
            $scope.json = JSON.stringify(newValue, null, 2);
            $scope.output = computed(newValue.group);
        }, true);   
        
        $scope.LoadPreviousVersion = function()
        {
            var ac = action ? action : "none";
            $scope.truefalse = true;
            $scope.data.pdbversion = "";
//                $scope.Demo.dt.vehiclename = "";
//                $scope.Demo.dt.modelname = "";
//                $window.alert(ac);
            $http({
                url : 'loadpdbversion_data',
                method : "POST",
                data : {"vehicle_id":$scope.data.vehicle, "action":ac}
            }).then(function (response, status, headers, config){
//                    alert(JSON.stringify(response.data.maps_object.pdbversion));
                $scope.array_result = [];
                $scope.status_value = "";
                var pdbLength = response.data.maps_object.pdbversion.length;
                if (pdbLength > 0) {
                    for(var i = 0; i < pdbLength; i++)
                    {
                         var data= response.data.maps_object.pdbversion[i];
 //                        $scope.data.pdbversion = response.data.maps_object.pdbversion[0].pversion;
 //                        $window.alert($scope.data.pdbversion);
                         $scope.array_result.push({
                             "pdbid":data.pid,
                             "pdbversion_name":parseFloat(data.pversion).toFixed(1),
                             "status":data.status
                         });
                     }
                     $scope.data.pdbversion = $scope.array_result[0];
                     $scope.LoadVehicleModels();
                     if($scope.data.pdbversion !== undefined){
                        $http({
                            url : 'loadsafetyversion_data',
                            method : "POST",
                            data : {"vehicle_id":$scope.data.vehicle, "action":ac}
                        }).then(function (response, status, headers, config){
//                                alert("response");
//                                alert(JSON.stringify(response.data.maps_object.legversion));
                            $scope.safarray_result = [];
                            $scope.status_value = "";
                            var safLength = response.data.maps_object.safversion.length;
                            if (safLength > 0) {
                                for(var i = 0; i < safLength; i++)
                                {
                                     var data= response.data.maps_object.safversion[i];
             //                        $scope.data.pdbversion = response.data.maps_object.pdbversion[0].pversion;
             //                        $window.alert($scope.data.pdbversion);
                                     $scope.safarray_result.push({
                                         "safid":data.sid,
                                         "safversion_name":parseFloat(data.sversion).toFixed(1),
                                         "status":data.status
                                     });
                                 }
//                                 alert(JSON.stringify($scope.safarray_result));
                                 $scope.data.safetyversion = $scope.safarray_result[0];
                            } else {
                                alert("No active Safety version found for this vehicle");
                            }
                            if($scope.data.safetyversion != undefined)
                                $scope.create_type = true;
            //                $scope.Demo.data = [{"vehiclename":"sasdsa","modelname":["dfsd","jhkjk","hkkjhk","kljk"],"versionname":"4.0","status":false}];
                        });
                    }   
                } else {
                    alert("No active PDB version found for this vehicle");
                }
//                $scope.Demo.data = [{"vehiclename":"sasdsa","modelname":["dfsd","jhkjk","hkkjhk","kljk"],"versionname":"4.0","status":false}];
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
                $scope.records = [];
                var result_data = response.data.maps_object.pdbversion[0];
                var modelid = result_data.modelid.split(",");
                var modelname = result_data.modelname.split(",");
                angular.forEach(modelid, function (value, key) {
                    $scope.records.push({"model_id":value,"modelname":modelname[key]});  

                });
            });
        };
        $scope.removeRow = function(qb_id)
        {		
//                alert(fid);
            var index = -1;		
            var comArr = eval( $scope.safety );
            for( var i = 0; i < comArr.length; i++ ) 
            {
                if( comArr[i].qb_id === qb_id ) 
                {
                    index = i;
                    break;
                }
            }
            if( index === -1 ) 
            {
                    alert( "Something gone wrong" );
            }
//                $scope.legislation_list.push({fid:comArr[index].fid,domain:comArr[index].domain,fea: comArr[index].fea})
            $scope.safety.splice( index, 1 );

//                var list_data =[];
//                $scope.list.filter(function(l,j){
//                    if(l.dfm_id != lid)
//                        list_data.push(l);
//                });
//                $scope.list = list_data;
//                alert(JSON.stringify($scope.list));

//                var list_index = -1;
//                $scope.list.filter(function(l,j){
//                    if(l.dfm_id == fid)
//                        list_index = j;
//                });
//                if(list_index != 1)
//                    $scope.list.splice( list_index, 1 );
        };
        $scope.radiovalue = function(qb_id,model_id,status)
        {		
//                alert("enter");
            if($scope.list.length === 0)
            {
                $scope.list.push({model_id:model_id,qb_id:qb_id,status:status});
            }
            else
            {
                var temp=0;
                for(var i=0; i<$scope.list.length; i++)
                {
                    if(($scope.list[i].model_id === model_id) && ($scope.list[i].qb_id === qb_id))
                    {
                        $scope.list[i].status=status;
                        temp=1;
                    }

                }
                if(temp==0)
                {
                    $scope.list.push({model_id:model_id,qb_id:qb_id,status:status});
                }
            }
//                alert(JSON.stringify($scope.list))
        };
        $scope.$on('notifyValue', function (event, args) {
            notification_to = args;
            $scope.createsafetyAjax("submit");
        });
        $scope.createsafetyAjax = function (event){
//                alert("createlegislationAjax");
            var status = $scope.data.status;
            if(status == undefined || status == false)
                notification_to = undefined;
            var data = {};
//                $scope.data.vehicle_id = $scope.data.vehicle;
            data['safetyversion'] = $scope.data;
            data['safetyversiondata_list'] = $scope.list;
            data['button_type'] = event;
            data['notification_to'] = notification_to+"";
//                alert(JSON.stringify(data));
            $http({
                url: 'createsafetyversion',
                method: "POST",
                data: data,
            }).then(function (response, status, headers, config) {
//                    $scope.vercompare_results = {"removed_features":"(d1) feature3, (d1) feature5", 
//                                                 "added_features":"(d1) feature4", 
//                                                 "removed_models":"m2,m4", "added_models":"m3", 
//                                                 "previous_version":"1.0", "current_version":"1.1"
//                                                };
//                      alert(JSON.stringify(response.data.maps_object));
//                      alert(JSON.stringify(response.data.maps_string));
                alert(response.data.maps_string.status);
                var vercompare_res = response.data.maps_object.saf_previous_data_result;
                if(vercompare_res != undefined){
                    $scope.vercompare_results = response.data.maps_object.saf_previous_data_result;
                    alert(JSON.stringify($scope.vercompare_results));
                }
                else{
                    alert("No any previous version found to compare");
                }
                $('#modal-comment').closeModal();
                if(response.data.maps_string.status_code == "1") {

                    var saf = JSON.parse(response.data.maps_string.saf_version.replace(/&quot;/g,'"'));
                    var safvg = JSON.parse(response.data.maps_string.saf_version_group.replace(/&quot;/g,'"'));
                    saf["froms"] = "Safety";
                    saf["t_id"] = $scope.t_id;
                    saf["tg_id"] = $scope.tg_id;
                    // if ($scope.t_id && $scope.tg_id) {
                    //     leg["t_id"] = prompt("Enter the Task ID");
                    //     leg["tg_id"] = prompt("Enter the Task Group ID");;
                    // } else {
                    //     leg["t_id"] = $scope.t_id;
                    //     leg["tg_id"] = $scope.tg_id;
                    // }
                    alert("leg "+ JSON.stringify(saf) +" legvg "+ JSON.stringify(safvg));
//                            alert("pdbv "+ JSON.stringify(pdbv) +" fro "+ JSON.stringify(pdbvg.froms));
//                            console.log("pdbv "+ JSON.stringify(pdbv) +" pdbvg "+ JSON.stringify(pdbvg));
                    $http({
                        url: 'insertTasks',
                        method: "POST",
                        data: {"val":saf},
                    }).then(function (response, status, headers, config) {
                        if (response.data.maps_object.success) {
                            alert(response.data.maps_object.success);
                            console.log(response.data.maps_object.success+" "+JSON.stringify(response.data.list_object));
                        } else {
                            alert(JSON.stringify(response.data.maps_object));
                        }
                    });
                    $window.open("safety_list.action","_self");
                }
            });
        };

        $scope.createsafetyversion = function (event)
        {
//              alert("createpdbversion");
//            var status = true;
        var status = $scope.data.status;
        if(status == undefined )
            status = false;
//            if (!$scope.doSubmit) 
//            {
//                return;
//            }
//            alert("before do submit");
//            $scope.doSubmit = false;  
//            alert(JSON.stringify($scope.records));
//            alert(JSON.stringify($scope.list));
//            alert($scope.list.length);
//            alert($scope.records.length * $scope.features.length);
        if($scope.list.length > 0){
                if($scope.list.length === $scope.records.length * $scope.safety.length){
                    if(status && event === "submit"){
                        $(".notifyPopup").click();
                    } else {
                        $scope.createsafetyAjax(event);
                    }
    //                    $scope.createpdbAjax(event);
                }
                else
                    alert("Please fill all the Safety status to create PDB version");
            }
            else{
                alert("Please fill the Safety status to create PDB version");
            }
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

        if (params_array[0].id && params_array[1].action) {
            $scope.data.pdbversion = params_array[0].id;
            var action = params_array[1].action;

//                var result_data = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));

            var safetydetail_list = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
            $window.alert("result_data_obj  "+JSON.stringify(safetydetail_list));
//                $scope.data.new_vehicle="select_vehicle";
            $scope.truefalse = true;
            $scope.data.status = safetydetail_list[0].status;
            $scope.data.vehicle = safetydetail_list[0].vehicle_id.toString();
            $scope.LoadPreviousVersion();
            $scope.safety = safetydetail_list;


//                var vehicledetail_list = result_data.vehicledetail_list;
//                $scope.data.status = result_data.pdbversion_status[0].status;
//
//                $scope.data.vehicleversion = vehicledetail_list[0].vehver_id.toString();
//                $scope.LoadSelectedVehicleVersionData();
//                $scope.data.vehiclename = vehicledetail_list[0].vehicle_id.toString();
//                $scope.records = vehicledetail_list;
//                    alert(JSON.stringify($scope.records));

//                var featuredetail_list = result_data.featuredetail_list;
//                for(var i=0; i<featuredetail_list.length; i++)
//                {
//                    if($scope.features.length === 0)
//                    {
//                        $scope.add_feature_tab(featuredetail_list[i].fid);
////                            $scope.features.push({fid:featuredetail_list[i].fid,fea:featuredetail_list[i].featurename,domain:featuredetail_list[i].domainname,status:featuredetail_list[i].status});
//                    }
//                    else
//                    {
//                        var temp=0;
//                        for(var j=0; j<$scope.features.length; j++)
//                        {
//                            if($scope.features[j].fid === featuredetail_list[i].fid)
//                            {
//                                temp=1;
//                            }
//                        }
//                        if(temp==0)
//                        {
//                            $scope.add_feature_tab(featuredetail_list[i].fid);
//                        }
//                    }
//
//                    $scope.radiovalue(featuredetail_list[i].fid,featuredetail_list[i].model_id,featuredetail_list[i].status);
////                        alert(JSON.stringify($scope.list));
//                }
            angular.element(function () {
                var result = document.getElementsByClassName("radio_button");
//                        alert(JSON.stringify(result));
                alert(JSON.stringify($scope.list));
                angular.forEach(result, function(value) {
                    var result_name = value.getAttribute("name").substring(1).split("_");
//                        alert(JSON.stringify(result_name));
                    var fid = result_name[0];
                    var model_id = result_name[1];
                    var status = value.getAttribute("value");
                    angular.forEach($scope.list, function(item) {
                        alert(item.qb_id+" "+item.model_id+" "+item.status);
                        if(item.qb_id === fid && item.model_id === model_id && item.status === status)
                            value.setAttribute("checked","checked");
                    });
                });
            });
            if(action === "view"){
                $scope.showProceed =false;
                $scope.showSave =false;
                $scope.showSubmit =false;
            } else if(action === "edit"){
                $scope.showProceed =true;
            }
        } else {
            $scope.t_id = params_array[0].t_id;
            $scope.tg_id = params_array[1].tg_id;
        }

      }

    });
    var queryBuilder = angular.module('queryBuilder', []);
    queryBuilder.directive('queryBuilder', ['$compile', function ($compile) {
        return {
            restrict: 'E',
            scope: {
                group: '='
            },
            templateUrl: '/queryBuilderDirective.html',
            compile: function (element, attrs) {
                var content, directive;
                content = element.contents().remove();
                return function (scope, element, attrs) {
                    scope.operators = [
                        { name: 'AND' },
                        { name: 'OR' }
                    ];

                    scope.fields = [
                        { name: 'f1' },
                        { name: 'f2' },
                        { name: 'f3' },
                        { name: 'f4' },
                        { name: 'f5' }
                    ];

                    scope.conditions = [

                        { name: '1' },
                        { name: '0' }
                        // { name: '=' },
                        // { name: '<>' },
                        // { name: '<' },
                        // { name: '<=' },
                        // { name: '>' },
                        // { name: '>=' }
                    ];

                    scope.addCondition = function () {
                        scope.group.rules.push({
                            condition: '=',
                            field: '',
                            data: ''
                        });
                    };

                    scope.removeCondition = function (index) {
                        scope.group.rules.splice(index, 1);
                    };

                    scope.addGroup = function () {
                        scope.group.rules.push({
                            group: {
                                operator: 'AND',
                                rules: []
                            }
                        });
                    };

                    scope.removeGroup = function () {
                        "group" in scope.$parent && scope.$parent.group.rules.splice(scope.$parent.$index, 1);
                    };

                    directive || (directive = $compile(content));

                    element.append(directive(scope, function ($compile) {
                        return $compile;
                    }));
                } 
            }
        }
    }]);

    $(document).ready(function(){
        // initialize modal
        $('.modal-trigger').leanModal();
    });
    </script>   
</body>

</html>          
