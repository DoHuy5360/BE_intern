## Lưu ý

#### Phân tích: `2023-03-27T13:28:50.278352600+07:00[Asia/Bangkok]`

> <br>
>
> `2023-03-27` : Năm - Tháng - Ngày
>
> `T` : Ký tự để phân tách
>
> `13:28:50.278352600`: Giờ : Phút : Giây
>
> `+07:00[Asia/Bangkok]`: Múi giờ
>
> <br>

<br>

#### Phân tích: `CC-346c849e-e04b-4cc8-b573-a051f84571c5`

> <br>
>
> `CC-` : Viết tắt của `Chấm Công`
>
> `346c849e-e04b-4cc8-b573-a051f84571c5` : Mã tự sinh
>
> <br>

<br>

# Tài khoản

## Tạo tài khoản

URL: `http://192.168.1.53:8080/api/v1/employee/store`

Body:

```json
{
	"accountEmail": "johndoe",
	"accountPassword": "password123",
	"accountRole": "johndoe@example.com",
	"headquarterId": "TS-1kjniu1n-13jn13j-13jnl",
	"employeePosition": "NV"
}
```

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

# Bảng Chấm Công

## Lấy tất cả bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/`
>
> Method : `GET`
>
> <br>

---

> Response

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

---

<br>
<br>

## Lấy 1 bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/{timekeepnigId}/show`
>
> Method : `GET`
>
> <br>

| Param           | Value                                     |
| --------------- | ----------------------------------------- |
| `timekeepnigId` | `CC-346c849e-e04b-4cc8-b573-a051f84571c5` |

> Response

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

---

<br>
<br>

## Lưu 1 bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/store`
>
> Method : `POST`
>
> <br>

---

> Body

```json
{
	"headquarterId": "Not Null",
	"employeeId": "Not Null",
	"timekeepingType": "Not Null",
	"timekeepingOut": "Allow Null",
	"timekeepingException": "Allow Null"
}
```

---

<br>
<br>

## Xóa 1 bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/{timekeepnigId}/delete`
>
> Method : `DELETE`
>
> <br>

| Param           | Value                                     |
| --------------- | ----------------------------------------- |
| `timekeepnigId` | `CC-346c849e-e04b-4cc8-b573-a051f84571c5` |

---

<br>
<br>

## Cập nhật 1 bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/{timekeepnigId}/update`
>
> Method : `PUT`
>
> <br>

| Param           | Value                                     |
| --------------- | ----------------------------------------- |
| `timekeepnigId` | `CC-346c849e-e04b-4cc8-b573-a051f84571c5` |

> Body

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

---
