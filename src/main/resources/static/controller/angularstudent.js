var mainApp = angular.module("mainApp", []);
var start_time = "" + new Date().getHours() + ":" + new Date().getMinutes() + ":00";

var subject_id;

const URL_ = "http://localhost:8080/api/v1";


mainApp.controller('timerController', function($scope, $http, $interval) {
    var timerr = document.getElementById("timer"); //timer element
    var intTag = null;
    var timerUrl = URL_ + "/exam/getMinutes/" + sessionStorage.getItem("subject_id");
    var url = URL_ + "/exam/createAnswer/";
    var fetchUrl = URL_ + "/exam/fetchAnswer/";
    var updateUrl = URL_ + "/exam/updateAnswer/";

    console.log("URL ");
    console.log(timerUrl);
    var config = {
        headers: {
            'Content-Type': 'application/json;charset=utf-8;'
        }
    }
    var minutesLeft;

    var callServer = function() {
        $http.get(timerUrl, config).then(response => {
            console.log('TIMER FUNCTION PINGED');
            console.log(response.data);
            minutesLeft = response.data;
            if (minutesLeft == 00) {
                var data = {};
                data.qp_id = document.getElementsByClassName('qp-idsecret')[0].id;
                data.answer = document.getElementById("answer_text").value;
                data.start_time = start_time;
                data.end_time = "" + new Date().getHours() + ":" + new Date().getMinutes() + ":00";
                $http.post(fetchUrl, data, config).then(Fetchresponse => {
                    console.log("Fetch KIA");
                    console.log(Fetchresponse.data);
                    if (Fetchresponse.data.qp_id == 0) {
                        // console.log('iske baad insertion hoga because null h');
                        // console.log(data);
                        //INSERTION

                        $http.post(url, data, config).then(response => {
                                console.log("Inserted");
                                if (response.data == 0) {
                                    $http.post(fetchUrl, data, config).then(secondlock => {
                                        data.answer = document.getElementById("answer_text").value;
                                        updateUrl = updateUrl + secondlock.data.answer_id;
                                        console.log(updateUrl);
                                        delete data['qp_id'];
                                        delete data['student_id'];
                                        console.log(data);
                                        $http.patch(updateUrl, data, config).then(response => {
                                            console.log("Updated SECONDLOCK " + response.data);
                                        }, error => {
                                            console.log("updation error");
                                            console.log(error);
                                            alert("Error Occured Update Dev Team");
                                        })

                                    }, error => {
                                        console.log("Second fetch error");
                                        console.log(error);
                                    })
                                }
                                // console.log(response);
                                //set backend to return string instead of answer_id
                                //take response.answer
                                //check if empty
                                //call update query to fix bug
                            }, error => {
                                console.log("Insertion Error");
                                console.log(error);
                                alert("Error Occured Update Dev Team");
                            })
                            // }
                    } else if ((Fetchresponse.data.answer == document.getElementById('answer_text').value)) {
                        console.log("Do NOTHING");
                    } else {
                        // console.log(Fetchresponse.data.answer);
                        // console.log(document.getElementById('answer_text').value);
                        // console.log( ? "true" : "false");
                        data.answer = document.getElementById("answer_text").value;
                        updateUrl = updateUrl + Fetchresponse.data.answer_id;
                        // console.log(updateUrl);
                        delete data['qp_id'];
                        delete data['student_id'];
                        delete data['qp_id'];
                        data.qp_id = document.getElementsByClassName('qp-idsecret')[0].id;
                        console.log(data);
                        $http.patch(updateUrl, data, config).then(response => {
                            console.log("Updated " + response.data);
                        }, error => {
                            console.log("updation error");
                            console.log(error);
                            alert("Error Occured Update Dev Team");
                        })

                    }
                }, error => {
                    console.log("Fetch Ero");
                    console.log(error);
                    alert("Error Occured Update Dev Team");

                })
                window.location.replace("/dashboard");
                alert("Time Up \n Your answers have been successfully submitted");
            }
            $scope.timer = minutesLeft + " Minutes Left";
        }, error => {
            console.log("Timer Error");
            console.log(error);
        })
    }
    callServer();
    $interval(callServer, (60 * 1000));
});

mainApp.controller('examController', function($scope, $http, $location) {
    $scope.upcoming = "Upcoming Exams";
    $scope.completed = " Completed Exams";
    console.log("Test");
    console.log($location);
    var url = URL_ + "/student/dashboard/";


    var config = {
        headers: {
            'Content-Type': 'application/json;charset=utf-8;'
        }
    }
    $http.get(url, config).then(function(response) {
        // console.log(response.data.Finished);
        console.log(response.data);
        $scope.completed = response.data.Finished;
        $scope.upcoming = response.data.Future;
        $scope.ongoing = response.data.Now;
        subject_id = response.data.Now[0].subject_id;
        sessionStorage.setItem('subject_id', subject_id);


    }, function error(response) {
        window.location.replace('/');
    });



});


mainApp.controller('ProfileController', function($scope, $http, $location) {
    $scope.breadcrumb = 'Profile';


    //	    var url = $location.absUrl() + "http://localhost:8080/api/v1/student/{id}";



    var url = URL_ + "/student/";


    var config = {
        headers: {
            'Content-Type': 'application/json;charset=utf-8;'
        }
    }
    $http.get(url, config).then(function(response) {
        $scope.response = response.data;

    }, function error(response) {
        window.location.replace('/dashboard');
    });

});

function startexam() {
    window.location.replace("/exam");
}

var qp_id;

mainApp.controller('QuestionController', function($scope, $http, $location) {

    // function to perform upon fetching qp from db
    console.log("IDR AAYA");
    console.log(sessionStorage.subject_id);
    var url = URL_ + "/exam/" + sessionStorage.subject_id;
    var config = {
        headers: {
            'Content-Type': 'application/json;charset=utf-8;'
        }
    }
    $http.get(url, config).then(function(response) {
        var i = 1;
        response.data.map(e => {
            e.index = i;
            i++;
        });
        i--;
        $scope.index = response.data;
        console.log(response.data);
        $scope.qp_id = response.data[0].qp_id;
        qp_id = response.data[0].qp_id;
        $scope.question = response.data[0].question;
        $scope.que_no = 1;
        //call db
        var url = URL_ + "/exam/fetchAnswer/";
        var data = {};
        data.qp_id = $scope.qp_id;
        // data.student_id = 1000004;
        console.log("First Fetch");
        console.log(data);
        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;',
            }
        }

        $http.post(url, data, config).then(response => {
            console.log('Fetched');
            console.log(response);
            if (response.data.qp_id != 0) {
                document.getElementById("answer_text").value = response.data.answer;
                document.getElementById("answer_text").innerHTML = response.data.answer;
                document.getElementById("answer_text").innerText = response.data.answer;
            } else
                document.getElementById("answer_text").value = "";
        }, error => {
            console.log(error);
        })




        $scope.switch_question = function(event) {
            // console.log(event);
            $scope.question = response.data[parseInt(event.target.id) - 1].question;
            $scope.qp_id = response.data[parseInt(event.target.id) - 1].qp_id;
            qp_id = response.data[parseInt(event.target.id) - 1].qp_id;
            // $scope.index = parseInt(event.target.id);
            // $scope.question = qp_array[$scope.que_no];
            $scope.que_no = parseInt(event.target.id);

            //call db
            var url = URL_ + "/exam/fetchAnswer/";
            var data = {};
            data.qp_id = qp_id;
            // data.student_id = 1000004;
            console.log(data);

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;',
                }
            }

            $http.post(url, data, config).then(response => {
                    if (response.data.qp_id != 0) {
                        document.getElementById("answer_text").value = response.data.answer;
                        document.getElementById("answer_text").innerHTML = response.data.answer;
                        document.getElementById("answer_text").innerText = response.data.answer;
                    } else
                        document.getElementById("answer_text").value = "";
                }, error => {
                    console.log(error);
                })
                // document.getElementById($scope.que_no).style.backgroundColor = "blue";
        }

        console.log($scope.index);

        //save and next button
        $scope.next = function() {
            //call db
            var url = URL_ + "/exam/createAnswer/";
            var fetchUrl = URL_ + "/exam/fetchAnswer/";
            var updateUrl = URL_ + "/exam/updateAnswer/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;',
                }
            }
            var data = {};
            // data.student_id = 1000004;
            data.qp_id = qp_id;
            data.answer = document.getElementById("answer_text").value;
            data.start_time = start_time;
            data.end_time = "" + new Date().getHours() + ":" + new Date().getMinutes() + ":00";
            console.log("Test");
            console.log(data);
            $http.post(fetchUrl, data, config).then(Fetchresponse => {
                console.log("Fetch KIA");
                console.log(Fetchresponse.data);
                if (Fetchresponse.data.qp_id == 0) {
                    // console.log('iske baad insertion hoga because null h');
                    // console.log(data);
                    //INSERTION
                    $http.post(url, data, config).then(response => {
                            console.log("Inserted");
                            if (response.data == 0) {
                                $http.post(fetchUrl, data, config).then(secondlock => {
                                    data.answer = document.getElementById("answer_text").value;
                                    updateUrl = updateUrl + secondlock.data.answer_id;
                                    console.log(updateUrl);
                                    delete data['qp_id'];
                                    delete data['student_id'];
                                    console.log(data);
                                    $http.patch(updateUrl, data, config).then(response => {
                                        console.log("Updated SECONDLOCK " + response.data);
                                    }, error => {
                                        console.log("updation error");
                                        console.log(error);
                                        alert("Error Occured Update Dev Team");
                                    })

                                }, error => {
                                    console.log("Second fetch error");
                                    console.log(error);
                                })
                            }
                            // console.log(response);
                            //set backend to return string instead of answer_id
                            //take response.answer
                            //check if empty
                            //call update query to fix bug
                        }, error => {
                            console.log("Insertion Error");
                            console.log(error);
                            alert("Error Occured Update Dev Team");
                        })
                        // }
                } else if ((Fetchresponse.data.answer == document.getElementById('answer_text').value)) {
                    console.log("Do NOTHING");
                } else {
                    // console.log(Fetchresponse.data.answer);
                    // console.log(document.getElementById('answer_text').value);
                    // console.log( ? "true" : "false");
                    data.answer = document.getElementById("answer_text").value;
                    updateUrl = updateUrl + Fetchresponse.data.answer_id;
                    // console.log(updateUrl);
                    delete data['qp_id'];
                    delete data['student_id'];
                    delete data['qp_id'];
                    data.qp_id = document.getElementsByClassName('qp-idsecret')[0].id;
                    console.log(data);
                    $http.patch(updateUrl, data, config).then(response => {
                        console.log("Updated " + response.data);
                    }, error => {
                        console.log("updation error");
                        console.log(error);
                        alert("Error Occured Update Dev Team");
                    })

                }
            }, error => {
                console.log("Fetch Ero");
                console.log(error);
                alert("Error Occured Update Dev Team");

            })



            // data.answer = document.getElementById("answer_text").value;
            // data.start_time = start_time;
            // data.end_time = "" + new Date().getHours() + ":" + new Date().getMinutes() + ":00";
            // console.log("dATA TO BE PASSED");
            // console.log(data);

            // var updateData = {};
            // updateData.answer = document.getElementById("answer_text").value;
            // data.answer = document.getElementById("answer_text").value;
            // var fetchData = {};
            // fetchData.student_id = 1000004;
            // fetchData.qp_id = qp_id;
            // $http.post(fetchUrl, fetchData, config).then(response => {
            //     if (response.data.answer != null) {
            //         // document.getElementById("answer_text").value = response.data.answer;
            //         url = URL_ + "/exam/updateAnswer/" + response.data.answer_id;
            //         console.log("Value is : " + document.getElementById("answer_text").value);
            //         console.log("Value2 is : " + document.getElementById("answer_text").innerText);
            //         console.log("Value3 is : " + document.getElementById("answer_text").innerHTML);
            //         console.log("Checking...");
            //         console.log(updateData);
            //         $http.patch(url, updateData, config).then(response => {
            //             console.log("Updated");
            //             console.log(response);
            //         }, error => {
            //             console.log("Update Error");
            //         })
            //     } else {

            //         data.start_time = start_time;
            //         data.end_time = "" + new Date().getHours() + ":" + new Date().getMinutes() + ":00";
            //         $http.post(url, data, config).then(response => {
            //             console.log("Inserted");
            //             console.log(response);
            //         }, error => {
            //             console.log("Insert Error");
            //             console.log(error);
            //         })
            //     }
            // }, error => {
            //     console.log(error);
            // })

            // var data = {};
            // data.student_id = 1000004;
            // data.qp_id = qp_id;
            // data.answer = document.getElementById("answer_text").value;
            // data.start_time = start_time;
            // data.end_time = "" + new Date().getHours() + ":" + new Date().getMinutes() + ":00";

            // i--;

            // if (i <= 0) {
            //     document.getElementById("submitExam").style.display = "block";
            // }


            // $http.post(url, data, config).then(response => {
            //     console.log(response);
            // }, error => {
            //     console.log(error);
            // })

            // if ($scope.que_no != response.data.length)
            //     document.getElementById("answer_text").value = "";



            if ($scope.que_no == response.data.length) {
                document.getElementById("loader").style.display = "block";
                setTimeout(() => {
                    document.getElementById($scope.que_no).style.backgroundColor = "green";
                    document.getElementById("loader").style.display = "none";
                }, 1500);


            } else {
                document.getElementById("loader").style.display = "block";
                setTimeout(() => {
                    document.getElementById($scope.que_no).style.backgroundColor = "green";
                    $scope.que_no = $scope.que_no + 1;
                    $scope.question = response.data[$scope.que_no - 1].question;
                    $scope.qp_id = response.data[$scope.que_no - 1].qp_id;
                    qp_id = response.data[$scope.que_no - 1].qp_id;
                    var forwardFetch = {};
                    forwardFetch.student_id = data.student_id;
                    forwardFetch.qp_id = qp_id;
                    $http.post(fetchUrl, forwardFetch, config).then(response => {

                        console.log("FORWRD FETCH");
                        console.log(response);
                        if (response.data.qp_id != 0) {
                            document.getElementById("answer_text").value = response.data.answer;
                            document.getElementById("answer_text").innerHTML = response.data.answer;
                            document.getElementById("answer_text").innerText = response.data.answer;
                        } else
                            document.getElementById("answer_text").value = "";
                        document.getElementById("loader").style.display = "none";
                    }, error => {
                        console.log(error);
                    })
                }, 1500);

            }



        }

        //previous button
        $scope.previous = function() {


            if ($scope.que_no == 1) {
                $scope.question = response.data[$scope.que_no - 1].question;
                qp_id = response.data[$scope.que_no - 1].qp_id;
                // console.log("Test:" + qp_id);
            } else {
                console.log("Called");
                $scope.que_no = $scope.que_no - 1;
                console.log(response.data[$scope.que_no - 1].question);
                $scope.question = response.data[$scope.que_no - 1].question;
                qp_id = response.data[$scope.que_no - 1].qp_id;
                // console.log("Test2:" + qp_id);
                //call db
                var url = URL_ + "/exam/fetchAnswer/";
                var data = {};
                data.qp_id = qp_id;
                // data.student_id = 1000004;
                console.log("previous fetch");
                console.log(data);

                var config = {
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8;',
                    }
                }

                $http.post(url, data, config).then(response => {
                    if (response.data.qp_id != 0) {
                        document.getElementById("answer_text").value = response.data.answer;
                        document.getElementById("answer_text").innerHTML = response.data.answer;
                        document.getElementById("answer_text").innerText = response.data.answer;
                    } else
                        document.getElementById("answer_text").value = "";
                }, error => {
                    console.log(error);
                })
            }



        }

        $scope.submit = function() {
            var checkUrl = URL_ + "/exam/check/" + sessionStorage.getItem('subject_id');
            $http.get(checkUrl, config).then(checkResponse => {
                if (checkResponse.data == 1) {
                    var option = confirm("Finish Exam?")
                    if (option) {
                        window.location.replace("/dashboard");
                    }
                } else
                    alert('Attempt Atleast One Question!');

            }, error => {
                console.log("Submit Error");
                alert("Some Error Occured Contact Dev Team");
            })
        }

    }, function error(response) {
        window.location.replace('/dashboard');
    });

});




mainApp.controller('resultController', function($scope, $http, $location) {

    var url ="http://localhost:8080/api/v1/result/";
    $http.get(url).then(function(response) {
        $scope.response = response.data;
/*        console.log("Result fetched!");
        console.log(response.data);
*/        
        var ResultKeys = [];
        Object.keys(response.data).map(key=>ResultKeys.push(key));
        var ResultValues = [];
        Object.values(response.data).map(key=>ResultValues.push(key));
        var ResultKeysObject = Object.assign({}, ResultKeys);
        $scope.response = response.data;
        console.log(ResultKeysObject);
        var ResultValuesObject = Object.assign({}, ResultValues);
  //      $scope.response = ResultValuesObject;
        console.log(ResultValuesObject);
//        ResultKeys.map(e=>console.log(e));
//        ResultValues.map(e=>console.log(e));
/*        $scope.response = JSON.parse(JSON.stringify(ResultKeys));
        console.log(JSON.parse(JSON.stringify(ResultKeys)));
*/        
        
    }, function error(response) {
    	alert("No result found!!");
       $scope.postResultMessage="Error with status"+ response.statusText;
    });
});

/*
mainApp.controller('resultController', function ($scope, $http) {
    $http({
        method:'GET',
        url: 'http://localhost:8080/api/v1/result/'
    }).then(function (response) { 
    	console.log("Result Fetched");
    	$scope.response = response.data;
    	},function erro(response){
    		console.log("Error");});
});
});
*/