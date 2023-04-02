## Lưu ý

#### Phân tích:

`2023-03-27T13:28:50.278352600+07:00[Asia/Bangkok]`

`2023-03-27` : Năm - Tháng - Ngày

`T` : Ký tự để phân tách

`13:28:50.278352600`: Giờ : Phút : Giây

`+07:00[Asia/Bangkok]`: Múi giờ

#### Phân tích:

`CC-346c849e-e04b-4cc8-b573-a051f84571c5`

`CC-` : Viết tắt của `Chấm Công` `346c849e-e04b-4cc8-b573-a051f84571c5` : Mã tự sinh

# Nhân viên

## Xem tất cả nhân viên

URL: `http://192.168.1.53:8080/api/v1/employee/`

Method: `GET`

Response:

```json
[
	{
		"employeeId": "NV-6ac91ec9-03ff-4da6-a04a-583a3fa17bce",
		"headquarterId": "TS-f06ec524-84db-4235-a8dd-cbc0f9f3abaa",
		"accountId": "TK-32a16183-699a-4022-8197-d3db35ddb5b0",
		"employeeName": null,
		"employeePhone": null,
		"employeeAddress": null,
		"employeeGender": null,
		"employeePosition": "NV",
		"employeeSalary": 0,
		"createAt": "2023-04-01T17:14:40.139462200+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-01T17:14:40.139462200+07:00[Asia/Bangkok]"
	}
]
```

## Thêm nhân viên

URL: `http://192.168.1.53:8080/api/v1/employee/store`

Body:

```json
{
	"accountEmail": "example@gmail.com",
	"accountPassword": "password123",
	"accountRole": "Admin",
	"headquarterId": "Gernerated by System",
	"employeePosition": "QL"
}
```

## Xem 1 nhân viên

URL: `http://192.168.1.53:8080/api/v1/employee/NV-6ac91ec9-03ff-4da6-a04a-583a3fa17bce`

Method: `GET`

Response:

```json
{
	"employeeId": "NV-6ac91ec9-03ff-4da6-a04a-583a3fa17bce",
	"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
	"accountId": "TK-32a16183-699a-4022-8197-d3db35ddb5b0",
	"employeeName": "Huy",
	"employeePhone": "0963723",
	"employeeAddress": "TP.HCM",
	"employeeGender": "1",
	"employeePosition": "QL",
	"employeeSalary": 100000,
	"createAt": "2023-04-01T17:14:40.139462200+07:00[Asia/Bangkok]",
	"updateAt": "2023-04-01T17:14:40.139462200+07:00[Asia/Bangkok]"
}
```

## Xem 1 nhân viên + Thông tin tài khoản

URL: `http://192.168.1.53:8080/api/v1/employee/NV-6ac91ec9-03ff-4da6-a04a-583a3fa17bce/information`

Method: `GET`

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"data": {
		"employeeId": "NV-6ac91ec9-03ff-4da6-a04a-583a3fa17bce",
		"accountId": "TK-32a16183-699a-4022-8197-d3db35ddb5b0",
		"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
		"employeeName": "Huy",
		"employeePhone": "0963723",
		"employeeAddress": "TP.HCM",
		"employeeGender": "1",
		"employeePosition": "QL",
		"employeeSalary": 100000,
		"accountEmail": "example@gmail.com",
		"accountRole": "Admin",
		"headquarterName": "Tru so 1",
		"headquarterAddress": "Ha Noi"
	}
}
```

## Cập nhật 1 nhân viên

URL: `http://192.168.1.53:8080/api/v1/employee/NV-6ac91ec9-03ff-4da6-a04a-583a3fa17bce/update`

Method: `PUT`

Body:

```json
{
	"headquarterId": "TS-f06ec524-84db-4235-a8dd-cbc0f9f3abaa",
	"employeeName": "Huy",
	"employeePhone": "0963723",
	"employeeAddress": "TP.HCM",
	"employeeGender": "1",
	"employeePosition": "QL",
	"employeeSalary": 100000
}
```

## Xóa 1 nhân viên

URL: `http://192.168.1.53:8080/api/v1/employee/NV-5de53312-94f7-47bf-b82f-9c6b84984e7b/delete`

Method: "DELETE"

# Trụ Sở

## Xem tất cả trụ sở

URL: `http://192.168.1.53:8080/api/v1/headquarter/`

Method: `GET`

Response:

```json
[
	{
		"headquarterId": "TS-f06ec524-84db-4235-a8dd-cbc0f9f3abaa",
		"headquarterName": "Tru so 1",
		"headquarterAddress": "TP.HCM",
		"createAt": "2023-04-01T16:30:50.821520600+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-01T16:30:50.821520600+07:00[Asia/Bangkok]"
	}
]
```

## Thêm trụ sở

URL: `http://192.168.1.53:8080/api/v1/headquarter/store`

Method: `POST`

Body:

```json
{
	"headquarterName": "Tru so 1",
	"headquarterAddress": "TP.HCM"
}
```

Response:

```json
{
	"headquarterId": "TS-97448b9c-23be-4b5d-9de3-97acbf22f91e",
	"headquarterName": "Tru so 1",
	"headquarterAddress": "Ha Noi",
	"createAt": "2023-04-02T23:01:24.964080600+07:00[Asia/Bangkok]",
	"updateAt": "2023-04-02T23:01:24.964080600+07:00[Asia/Bangkok]"
}
```

## Cập nhật 1 trụ sở

URL: `http://192.168.1.53:8080/api/v1/headquarter/TS-f06ec524-84db-4235-a8dd-cbc0f9f3abaa/update`

Method: `POST`

Body:

```json
{
	"headquarterName": "Trụ sở 2",
	"headquarterAddress": "TP.HCM"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Cập Nhật Thành Công",
	"entity": {
		"headquarterId": "TS-80d47df1-8028-4520-b796-c403ae1d0a47",
		"headquarterName": "Trụ sở 2",
		"headquarterAddress": "TP.HCM",
		"createAt": "2023-04-02T22:48:29.249894800+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-02T22:48:29.249894800+07:00[Asia/Bangkok]"
	}
}
```

## Xóa 1 trụ sở

URL: `http://192.168.1.53:8080/api/v1/headquarter/TS-f06ec524-84db-4235-a8dd-cbc0f9f3abaa/update`

Method: `DELETE`

Response:

```json
{
	"status": "OK",
	"message": "Xóa Thành Công",
	"entity": null
}
```

# Bảng Chấm Công

## Lấy tất cả bản ghi trong bảng Chấm công

URL: `http://localhost:8080/api/v1/timekeeping/`

Method : `GET`

Response:

```json
[
	{
		"timekeepingId": "CC-346c849e-e04b-4cc8-b573-a051f84571c5",
		"headquarterId": "TS-383298924",
		"employeeId": "NV-48274924",
		"timekeepingType": "Postman",
		"timekeepingIn": "2023-03-27T13:28:50.278352600+07:00[Asia/Bangkok]",
		"timekeepingOut": null,
		"timekeepingException": null,
		"createAt": "2023-03-27T13:28:50.295981900+07:00[Asia/Bangkok]",
		"updateAt": "2023-03-27T13:28:50.295981900+07:00[Asia/Bangkok]"
	}
]
```

## Lấy 1 bản ghi trong bảng Chấm công

URL: `http://localhost:8080/api/v1/timekeeping/CC-346c849e-e04b-4cc8-b573-a051f84571c5/show`

Method : `GET`

Response:

```json
{
	"timekeepingId": "CC-346c849e-e04b-4cc8-b573-a051f84571c5",
	"headquarterId": "TS-383298924",
	"employeeId": "NV-48274924",
	"timekeepingType": "Postman",
	"timekeepingIn": "2023-03-27T13:28:50.278352600+07:00[Asia/Bangkok]",
	"timekeepingOut": null,
	"timekeepingException": null,
	"createAt": "2023-03-27T13:28:50.295981900+07:00[Asia/Bangkok]",
	"updateAt": "2023-03-27T13:28:50.295981900+07:00[Asia/Bangkok]"
}
```

## Lưu 1 bản ghi trong bảng Chấm công

URL: `http://localhost:8080/api/v1/timekeeping/store`

Method : `POST`

Body:

```json
{
	"headquarterId": "Not Null",
	"employeeId": "Not Null",
	"timekeepingType": "Not Null",
	"timekeepingOut": "Allow Null",
	"timekeepingException": "Allow Null"
}
```

## Xóa 1 bản ghi trong bảng Chấm công

URL: `http://localhost:8080/api/v1/timekeeping/CC-346c849e-e04b-4cc8-b573-a051f84571c5/delete`

Method : `DELETE`

## Cập nhật 1 bản ghi trong bảng Chấm công

URL: `http://localhost:8080/api/v1/timekeeping/CC-346c849e-e04b-4cc8-b573-a051f84571c5/update`

Method : `PUT`

Body

```json
{
	"headquarterId": "Allow update",
	"employeeId": "Allow update",
	"timekeepingType": "Allow update",
	"timekeepingIn": "Allow update",
	"timekeepingOut": "Allow update",
	"timekeepingException": "Allow update"
}
```
