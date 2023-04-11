# Lưu ý

## Phân tích:

`2023-03-27T13:28:50.278352600+07:00[Asia/Bangkok]`

`2023-03-27` : Năm - Tháng - Ngày

`T` : Ký tự để phân tách

`13:28:50.278352600`: Giờ : Phút : Giây

`+07:00[Asia/Bangkok]`: Múi giờ

## Phân tích:

`CC-346c849e-e04b-4cc8-b573-a051f84571c5`

`CC-` : Viết tắt của `Chấm Công` `346c849e-e04b-4cc8-b573-a051f84571c5` : Mã tự sinh

---

# Nhân viên

## Quyền truy cập: Manager

### Xem tất cả nhân viên

Url: `https://be-intern.onrender.com/api/v2/employee/`

Method: `GET`

Body:

```json

```

Response:

```json
{
    "status": "OK",
    "message": "Đọc Thành Công",
    "amount": 11,
    "data": [
        {
            "employeeId": "NV-390e0b32-b189-4822-b6a7-e6f3459c425b",
            "headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
            "accountId": "TK-3101360e-83d7-4c91-a115-cd800a4eb4f7",
            "employeeName": null,
            "employeePhone": null,
            "employeeAvatar": "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg",
            "employeeAddress": null,
            "employeeGender": null,
            "employeePosition": "Employee",
            "employeeSalary": 0,
            "createAt": "2023-04-05T11:32:50.981302500+07:00[Asia/Bangkok]",
            "updateAt": "2023-04-05T11:32:50.981302500+07:00[Asia/Bangkok]"
        }
	]
{
```

### Xem 1 nhân viên

Url: `https://be-intern.onrender.com/api/v2/employee/{employee_id}/show`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 0,
	"data": {
		"employeeId": "NV-390e0b32-b189-4822-b6a7-e6f3459c425b",
		"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
		"accountId": "TK-3101360e-83d7-4c91-a115-cd800a4eb4f7",
		"employeeName": null,
		"employeePhone": null,
		"employeeAvatar": "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg",
		"employeeAddress": null,
		"employeeGender": null,
		"employeePosition": "Employee",
		"employeeSalary": 0,
		"createAt": "2023-04-05T11:32:50.981302500+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-05T11:32:50.981302500+07:00[Asia/Bangkok]"
	}
}
```

### Tạo 1 nhân viên + 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/employee/store`

Method: `POST`

Body:

```json
{
	"accountEmail": "example@gmail.com",
	"accountPassword": "a",
	"accountRole": "Employee",
	"headquarterId": "TS-9ce2aa19-1ee1-493f-83f0-c2b7bd88264c",
	"employeePosition": "QL"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Lưu Thành Công",
	"amount": 0,
	"data": null
}
```

### Tạo nhiều nhân viên + nhiều tài khoản ( Beta )

Url: `https://be-intern.onrender.com/api/v2/employee/multiple-store`

Method: `POST`

Body:

```json

```

Response:

```json

```

### Xem tất cả nhân viên + tài khoản

Url: `https://be-intern.onrender.com/api/v2/employee/all-information`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 0,
	"data": [
		{
			"employeeId": "NV-92973606-6197-4e5a-887b-36b800b20e57",
			"accountId": "TK-e0bd0cee-6e67-4551-898a-cc92250a828c",
			"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
			"employeeName": "Huy",
			"employeePhone": "0963758993",
			"employeeAddress": "TP.HCM",
			"employeeGender": "1",
			"employeePosition": "QL",
			"employeeAvatar": "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg",
			"employeeSalary": 100000,
			"accountEmail": "manager@gmail.com",
			"accountRole": "Manager",
			"headquarterName": "Tru so 1",
			"headquarterAddress": "Ha Noi"
		}
	]
}
```

### Xóa 1 nhân viên

Url: `https://be-intern.onrender.com/api/v2/employee/{employee_id}/delete`

Method: `DELETE`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Xóa Thành Công",
	"amount": 0,
	"data": null
}
```

### Cập nhật 1 nhân viên

Url: `https://be-intern.onrender.com/api/v2/employee/{id}/update`

Method: `PUT`

Body:

```json
{
	"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
	"employeeName": "Huy",
	"employeePhone": "0963758993",
	"employeeAddress": "TP.HCM",
	"employeeGender": "1",
	"employeePosition": "QL",
	"employeeSalary": 100000
}
```

Response:

```json
{
	"status": "OK",
	"message": "Cập Nhật Thành Công",
	"amount": 0,
	"data": {
		"employeeId": "NV-8aa892c4-8f5b-40c7-ac4a-510d6d350d72",
		"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
		"accountId": "TK-984d235d-ebb8-42e9-914c-8a7e042c1efd",
		"employeeName": "Huy",
		"employeePhone": "0963758993",
		"employeeAvatar": "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg",
		"employeeAddress": "TP.HCM",
		"employeeGender": "1",
		"employeePosition": "QL",
		"employeeSalary": 100000,
		"createAt": "2023-04-06T11:30:08.745193+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-06T11:30:08.745193+07:00[Asia/Bangkok]"
	}
}
```

## Quyền truy cập: Manager & Employee

### Xem thông tin 1 nhân viên (bản thân)

Url: `https://be-intern.onrender.com/api/v2/employee/information`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 0,
	"data": {
		"employeeId": "NV-92973606-6197-4e5a-887b-36b800b20e57",
		"accountId": "TK-e0bd0cee-6e67-4551-898a-cc92250a828c",
		"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
		"employeeName": "Huy",
		"employeePhone": "0963758993",
		"employeeAddress": "TP.HCM",
		"employeeGender": "1",
		"employeePosition": "QL",
		"employeeAvatar": "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg",
		"employeeSalary": 100000,
		"accountEmail": "manager@gmail.com",
		"accountRole": "Manager",
		"headquarterName": "Tru so 1",
		"headquarterAddress": "Ha Noi"
	}
}
```

### Cập nhật thông tin nhân viên (bản thân)

Url: `https://be-intern.onrender.com/api/v2/employee/self-update`

Method: `PUT`

Body:

```json
{
	"employeeName": "Huy",
	"employeePhone": "0963723",
	"employeeAddress": "TP.HCM",
	"employeeGender": "1"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Cập Nhật Thành Công",
	"amount": 0,
	"data": {
		"employeeId": "NV-92973606-6197-4e5a-887b-36b800b20e57",
		"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
		"accountId": "TK-e0bd0cee-6e67-4551-898a-cc92250a828c",
		"employeeName": "Huy",
		"employeePhone": "0963723",
		"employeeAvatar": "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg",
		"employeeAddress": "TP.HCM",
		"employeeGender": "1",
		"employeePosition": "QL",
		"employeeSalary": 100000,
		"createAt": "2023-04-05T23:14:14.245946600+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-05T23:14:14.245946600+07:00[Asia/Bangkok]"
	}
}
```

### Cập nhật ảnh đại diện nhân viên (bản thân)

Url: `https://be-intern.onrender.com/api/v2/employee/create-avatar`

Method: `POST`

Body:

| Key  | Value                 |
| ---- | --------------------- |
| file | 1 file ảnh [png, jpg] |

# Trụ sở

### Xem tất cả trụ sỏ

Url: `https://be-intern.onrender.com/api/v2/headquarter/`

Method: `GET`

Body:

```json

```

Response:

```json

```

### Tạo 1 trụ sỏ

Url: `https://be-intern.onrender.com/api/v2/headquarter/store`

Method: `POST`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 3,
	"data": [
		{
			"headquarterId": "TS-ed0d156f-5cfa-4f90-aa05-b2cf2aac2708",
			"headquarterName": "Tru so 1",
			"headquarterAddress": "Ha Noi",
			"createAt": "2023-04-02T23:34:54.405190300+07:00[Asia/Bangkok]",
			"updateAt": "2023-04-02T23:34:54.405190300+07:00[Asia/Bangkok]"
		}
	]
}
```

### Cập nhật 1 trụ sỏ

Url: `https://be-intern.onrender.com/api/v2/headquarter/{headquarter_id}/update`

Method: `PUT`

Body:

```json
{
	"headquarterName": "Trụ sở 4",
	"headquarterAddress": "TP.HCM"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Cập Nhật Thành Công",
	"amount": 0,
	"data": {
		"headquarterId": "TS-62f1cdc1-cce1-451b-a160-c0b8a6177fc9",
		"headquarterName": "Trụ sở 4",
		"headquarterAddress": "TP.HCM",
		"createAt": "2023-04-08T21:06:20.788794400+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-08T21:06:20.788794400+07:00[Asia/Bangkok]"
	}
}
```

### Xóa 1 trụ sỏ

Url: `https://be-intern.onrender.com/api/v2/headquarter/{headquarter_id}/detele`

Method: `DELETE`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Xóa Thành Công",
	"amount": 0,
	"data": null
}
```

# Lịch làm việc

## Quyền truy cập: Manager

### Xem tất cả lịch làm việc

Url: `https://be-intern.onrender.com/api/v2/workschedule/`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 2,
	"data": [
		{
			"workScheduleId": "WS-b368072b-5d3a-4141-b46a-3ceaf4094748",
			"employeeId": "NV-e81390c9-9df4-44ed-929b-6b41adcbc46f",
			"workScheduleColor": "black",
			"workSchedulePlan": "Quét nhà X",
			"workSchedulePlace": "Công Tác",
			"workScheduleTime": "2023-04-05T12:54:31.086993200+07:00[Asia/Bangkok]",
			"createAt": "2023-04-03T11:32:08.127998200+07:00[Asia/Bangkok]",
			"updateAt": "2023-04-03T11:32:08.127998200+07:00[Asia/Bangkok]"
		}
	]
}
```

### Xem tất cả lịch làm việc + thông tin nhân viên + trụ sở

Url: `https://be-intern.onrender.com/api/v2/workschedule/all-information`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 0,
	"data": [
		{
			"workScheduleId": "WS-f839472f-ca7a-4441-bf9e-408d1ebfcd5d",
			"employeeId": "NV-92973606-6197-4e5a-887b-36b800b20e57",
			"employeeName": "Huy",
			"employeePhone": "0963723",
			"employeeAvatar": "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg",
			"employeePosition": "QL",
			"workSchedulePlan": "Quet Nha 2",
			"workScheduleTime": "2023-04-03T11:31:40.238996200",
			"workScheduleColor": null,
			"workSchedulePlace": null,
			"headquarterName": "Tru so 1",
			"headquarterAddress": "Ha Noi"
		}
	]
}
```

### Xem 1 lịch làm việc

Url: `https://be-intern.onrender.com/api/v2/workschedule/{work_schedule_id}/show`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 0,
	"data": {
		"workScheduleId": "WS-f839472f-ca7a-4441-bf9e-408d1ebfcd5d",
		"employeeId": "NV-92973606-6197-4e5a-887b-36b800b20e57",
		"workScheduleColor": null,
		"workSchedulePlan": "Quet Nha 2",
		"workSchedulePlace": null,
		"workScheduleTime": "2023-04-03T11:31:40.238996200+07:00[Asia/Bangkok]",
		"createAt": "2023-04-03T11:31:40.241388100+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-03T11:31:40.241388100+07:00[Asia/Bangkok]"
	}
}
```

## Quyền truy cập: Manager & Employee

### Tạo 1 lịch làm việc

Url: `https://be-intern.onrender.com/api/v2/workschedule/store`

Method: `POST`

Body:

```json
{
	"workScheduleColor": "green",
	"workSchedulePlan": "Quet Nha 3",
	"workSchedulePlace": "Đi gặp đối tác"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Lưu Thành Công",
	"amount": 0,
	"data": {
		"workScheduleId": "WS-f7099f75-0b02-49b8-992b-a188fb9e8ae5",
		"employeeId": "NV-92973606-6197-4e5a-887b-36b800b20e57",
		"workScheduleColor": "green",
		"workSchedulePlan": "Quet Nha 3",
		"workSchedulePlace": "Đi gặp đối tác",
		"workScheduleTime": "2023-04-08T21:11:33.231872600+07:00[Asia/Bangkok]",
		"createAt": "2023-04-08T21:11:33.231872600+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-08T21:11:33.231872600+07:00[Asia/Bangkok]"
	}
}
```

### Xem 1 lịch làm việc (bản thân)

Url: `https://be-intern.onrender.com/api/v2/workschedule/self-schedule`

Method: `GET`

Body:

```json

```

Response:

```json

```

### Xóa lịch làm việc (bản thân)

Url: `https://be-intern.onrender.com/api/v2/workschedule/{id}/delete`

Method: `DELETE`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 2,
	"data": [
		{
			"workScheduleId": "WS-f839472f-ca7a-4441-bf9e-408d1ebfcd5d",
			"employeeId": "NV-92973606-6197-4e5a-887b-36b800b20e57",
			"workScheduleColor": null,
			"workSchedulePlan": "Quet Nha 2",
			"workSchedulePlace": null,
			"workScheduleTime": "2023-04-03T11:31:40.238996200+07:00[Asia/Bangkok]",
			"createAt": "2023-04-03T11:31:40.241388100+07:00[Asia/Bangkok]",
			"updateAt": "2023-04-03T11:31:40.241388100+07:00[Asia/Bangkok]"
		}
	]
}
```

### Cập nhật lịch làm việc (bản thân)

Url: `https://be-intern.onrender.com/api/v2/workschedule/{work_schedule_id}/update`

Method: `PUT`

Body:

```json
{
	"workScheduleColor": "black",
	"workSchedulePlan": "Quét nhà XYZ",
	"workSchedulePlace": "Công Tác"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Cập Nhật Thành Công",
	"amount": 0,
	"data": null
}
```

# Tài khoản

## Quyền truy cập: Manager

### Xem tất cả tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 7,
	"data": [
		{
			"accountId": "TK-aafd8339-74f6-4873-a816-713a9022211a",
			"accountEmail": "example2@gmail.com",
			"accountPassword": "1",
			"retypeAccountPassword": null,
			"accountRole": "Admin",
			"createAt": "2023-04-06T11:13:24.101982100+07:00[Asia/Bangkok]",
			"updateAt": "2023-04-06T11:13:24.107085200+07:00[Asia/Bangkok]"
		}
	]
}
```

### Xem 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/{account_id}/show`

Method: `GET`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Đọc Thành Công",
	"amount": 0,
	"data": {
		"accountId": "TK-aafd8339-74f6-4873-a816-713a9022211a",
		"accountEmail": "example2@gmail.com",
		"accountPassword": "1",
		"retypeAccountPassword": null,
		"accountRole": "Admin",
		"createAt": "2023-04-06T11:13:24.101982100+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-06T11:13:24.107085200+07:00[Asia/Bangkok]"
	}
}
```

### Tạo 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/store`

Method: `POST`

Body:

```json
{
	"email": "super@gmail.com",
	"password": "123",
	"role": "Admin"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Lưu Thành Công",
	"amount": 0,
	"data": {
		"accountId": "TK-0d07f2ae-719e-495e-915d-96b973e7edb5",
		"accountEmail": null,
		"accountPassword": null,
		"retypeAccountPassword": null,
		"accountRole": null,
		"createAt": "2023-04-08T21:25:13.624613600+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-08T21:25:13.625614400+07:00[Asia/Bangkok]"
	}
}
```

### Cập nhật 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/{account_id}/update`

Method: `PUT`

Body:

```json
{
	"accountRole": "Admin",
	"accountEmail": "huy@gmail.com",
	"accountPassword": "1",
	"retypeAccountPassword": "1"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Cập Nhật Thành Công",
	"amount": 0,
	"data": {
		"accountId": "TK-0d07f2ae-719e-495e-915d-96b973e7edb5",
		"accountEmail": "huy@gmail.com",
		"accountPassword": null,
		"retypeAccountPassword": null,
		"accountRole": "Admin",
		"createAt": "2023-04-08T21:25:13.624613600+07:00[Asia/Bangkok]",
		"updateAt": "2023-04-08T21:25:13.625614400+07:00[Asia/Bangkok]"
	}
}
```

### Xóa 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/{account_id}/delete`

Method: `DELETE`

Body:

```json

```

Response:

```json
{
	"status": "OK",
	"message": "Xóa Thành Công",
	"amount": 0,
	"data": null
}
```

## Quyền truy cập: Manager & Employee

### Đổi mật khẩu

Url: `https://be-intern.onrender.com/api/v2/account/reset-password`

Method: `PUT`

Body:

```json
{
	"accountEmail": "huy@gmail.com",
	"accountPassword": "a",
	"retypeAccountPassword": "a"
}
```

Response:

```json
{
	"status": "OK",
	"message": "Cập Nhật Thành Công",
	"amount": 0,
	"data": null
}
```
