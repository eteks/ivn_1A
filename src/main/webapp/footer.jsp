<a class="waves-effect waves-light modal-trigger notifyPopup" href="#modal-notification" ></a>
</div><!-- row -->
</div><!-- page body -->
</div><!-- page wrapper -->

</div>
</div>
</div>
</div>
</div>
</div>
</div>


<div id="modal-notification" class="modal" ng-init="getAllCount()" ng-controller="RecordCtrl">
    <div class="modal-content">
        <h5 class="text-c-red m-b-25">Notification <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>

        <div class="form-group" ng-repeat="x in groups">
            <!--<label for="name">Domain</label>-->
            <!--                    <div class="border-checkbox-section">                                                                                    
                                    <div class="border-checkbox-group border-checkbox-group-success">
                                        <input class="border-checkbox checkbox_can_act" type="checkbox" ng-click="notify(x.id)" ng-model="nw_checkbox">
                                        <label class="border-checkbox-label" for="checkbox_c_{{x.id}}"></label>
                                    </div>
                                </div>-->
            <!--<input type="checkbox" ng-model="x.id">-->
            <input type="checkbox" name="chk[]" ng-model="lst" value="{{x.group_name}}" ng-change="change(lst,x.id)">
            {{x.group_name}}
        </div>
        <div class="input-field text-right">
            <button id="btn-create-product" class="waves-effect waves-light btn btn-primary  float-right" ng-click="createfeature()">Notify</button>
        </div>
    </div>
</div>
 <!-- modal for for creating new product -->
    <div id="edit_version" class="modal" ng-controller="OperationCtrl">
        <div class="modal-content">
            <h5 class="text-c-red m-b-25">Select Operation <a class="modal-action modal-close waves-effect waves-light float-right m-t-5"> <strong><em>Close</em></strong></a></h5>

                <div class="split1">
                    <input ng-model="generate" type="radio" class="validate col-lg-12" value="obtain"/><label>Obtain Values</label></br>
                    <input ng-model="generate" type="radio" class="validate col-lg-12" value="edit"/><label>Edit Version</label>
                </div>
                <div class="input-field text-center">
                    <!--<a id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em float-right" ng-click="createfeature()">Add</a>-->
                    <!--<button id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em btn-primary" ng-click="createfeature_and_domain()" ng-mousedown='doSubmit=true' name="add">Proceed</button>-->
                    <button id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em btn-primary" ng-click="proceed()" ng-mousedown='doSubmit=true' name="add">Proceed</button>
                </div>
        </div>
    </div>
<!-- Required Jquery -->
<script src="js/lib/jquery.min.js"></script>
<!-- <script type="text/javascript" src="../bower_components/jquery-ui/js/jquery-ui.min.js"></script> -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>    

<!-- modernizr js -->
<script type="text/javascript" src="js/modernizr.js"></script>

<!-- Custom js -->
<script type="text/javascript" src="js/custom-dashboard.min.js"></script>
<script src="js/pcoded.min.js"></script>
<script src="js/demo-12.js"></script>
<script type="text/javascript" src="js/script.min.js"></script>
<!-- Custom js -->

<!-- include jquery -->

<!-- material design js -->
<!--<script src="js/materialize.min.js"></script>-->
<script src="js/lib/angular.min.js"></script>  
<!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.13/angular.js"></script>--> 

<script src="js/ng-tags-input.min.js"></script>
<script src="js/lib/materialize.min.js"></script>
<!--<script type="text/javascript" src="js/jQuery.extendext.js"></script> -->
<!--<script type="text/javascript" src="js/select2.js"></script>-->
<script type="text/javascript" src="js/query-builder.min.js"></script>
<script type="text/javascript" src="js/sql-parser.min.js"></script>
<!--<script type="text/javascript" src="js/combineBuilder.js"></script>-->
<script type="text/javascript" src="js/select2.full.min.js"></script>
<script type="text/javascript" src="js/select2-custom.js"></script>
<script src="js/tabs.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.18/angular-sanitize.min.js"></script>
<script src="js/angular-query-builder.js"></script>
<!--    <script src="https://cdnjs.cloudflare.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angularjs/1.6.9/angular-sanitize.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap.js"></script>    -->
<script src="js/dirPagination.js"></script>
<script>
                    var app = angular.module('angularTable', ['angularUtils.directives.dirPagination', 'ngTagsInput','tabs','ngSanitize','queryBuilder']);
//        var app = angular.module('angularTable', ['ui.bootstrap']);
                app.controller('RecordCtrl', function ($scope, $http,$rootScope)
                {
                    $http.get("dashboardata.action").then(function (data, status, headers, config)
                    {
                        $scope.activeMenu = [];
                        $scope.groups = data.data.group_result;
                        //console.log(data.data.group_result);

//                        $scope.vehiclecount = data.data.count_result.vehiclecount;
//                        $scope.modelcount = data.data.count_result.modelcount;
//                        $scope.vehicleversion_count = data.data.count_result.vehicleversion_count;
//                        $scope.pdbversion_count = data.data.count_result.pdbversion_count;
//                        $scope.pdbfeatures_count = data.data.count_result.pdbfeatures_count;
//                        $scope.acbversion_count = data.data.count_result.ivnversion_count;
//                        $scope.count = {"ivnversion_count": data.data.count_result.ivnversion_count,
//                            "ecucount": data.data.count_result.ecucount
//                        };
//                               alert(JSON.stringify($scope.groups));
                    });
//                    var url = $location.absUrl();
//                    alert(urli);
//                    $scope.setActive = function(menuItem) 
//                    {
//                       $scope.activeMenu = menuItem
//                    }
                    $scope.lst = [];
                    $scope.change = function(check,value){
                    if(check){
                        $scope.lst.push(value);
                    }else{
                         $scope.lst.splice($scope.lst.indexOf(value), 1);
                    }
                    };
                    $scope.createfeature = function(){                       
                        $rootScope.$broadcast('notifyValue', $scope.lst);
                        $(".modal-close").click();
                    };
                });
                app.controller('notificationController', function ($scope, $http, $window) {
                    $http.get("unreadnotification.action").then(function (response, data, status, headers, config) {
                        $scope.addlist = response.data.notification_result;
                        $window.alert("@!########@$#%$#%^#$^%$^%$^$%   "+JSON.stringify($scope.addlist));
                        console.log("@!########@$#%$#%^#$^%$^%$^$%   "+response.data.notification_result);
                        //$scope.count = response.data.notification_result.length;
                        $scope.count = 0;
                        angular.forEach($scope.addlist, function(value, key) {
                            // Increment each number by one when you hit it
                            if (value.status == false){
                                $scope.count++;
                            }
                        });
                    });
                    $scope.view = function (id) {
                        $http.get("readnotification.action?notification_id=" + id).then(function (response, data, status, headers, config) {
                            $window.open(response.data.view_notification[0].version_type + ".action?id=" + response.data.view_notification[0].version_id + "&action=view", "_self");
                        });
                    }
                });
                $(document).ready(function () {
                    // initialize modal
                    $('.modal-trigger').leanModal();
                });
</script>
