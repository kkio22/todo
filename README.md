# API 명세서 (사용자)
https://github.com/kkio22/todo/blob/main/README.md
| 기능             	| HTTP METHOD 	| URL      	| PathVariable 	| RequestParam 	| RequestBody                                                          	| Response                                                                                                                                                                        	| HttpStatus                      	|
|------------------	|-------------	|----------	|--------------	|--------------	|----------------------------------------------------------------------	|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|---------------------------------	|
| 사용자 조회      	| GET         	| users    	| X            	| X            	| X                                                                    	| [<br>{<br>    id:1<br>    "username": "Alice",<br>    "email": "Alice@email.com"<br>},<br><br>{<br>    id:2<br>    "username": "god",<br>    "email": "god@email.com"<br>}<br>] 	|                                 	|
| 사용자 단일 조회 	| GET         	| users/id 	| long id      	| X            	| X                                                                    	| {<br>    id:1<br>    "username": "Alice",<br>    "email": "Alice@email.com"<br>}                                                                                                	|                                 	|
| 사용자 생성      	| POST        	| users    	| X            	| X            	| {<br>    "username": "Alice",<br>    "email": "Alice@email.com"<br>} 	| {<br>    id:1<br>    "username": "Alice",<br>    "email": "Alice@email.com"<br>}                                                                                                	| 201 CREATED                     	|
| 사용자 일부 수정 	| PATCH       	| users/id 	| long id      	| X            	| {<br>    "username": "Rice",<br>    "email": "Rice@email.com"<br>}   	| {<br>    id:1    <br>    "username": "Rice",<br>    "email": "Rice@email.com"<br>}                                                                                              	| 200 OK                          	|
| 사용자 삭제      	| DELET       	| users/id 	| long id      	| X            	| X                                                                    	| X                                                                                                                                                                               	| msg: "사용자가 삭제되었습니다." 	|




![Image](https://github.com/user-attachments/assets/16532abd-8981-4263-a715-4833b183c9e4)
