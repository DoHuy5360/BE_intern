# Lấy tất cả bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/`
>
> Method : <strong style="color: red;">GET</strong>
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

<div style="width: 100%; height: 10px; background: #185684;"></div>
<br>
<br>

# Lấy 1 bản ghi trong bảng Chấm công

> <br>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/`<strong style="color: yellow;">timekeepnigId</strong>`/show`
>
> Method : <strong style="color: red;">GET</strong>
>
> <br>

| Param                                                 | Value                                     |
| ----------------------------------------------------- | ----------------------------------------- |
| <strong style="color: yellow;">timekeepnigId</strong> | `CC-346c849e-e04b-4cc8-b573-a051f84571c5` |

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

<div style="width: 100%; height: 10px; background: #185684;"></div>
<br>
<br>

# Lưu 1 bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/store`
>
> Method : <strong style="color: red;">POST</strong>
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

<div style="width: 100%; height: 10px; background: #185684;"></div>
<br>
<br>

# Xóa 1 bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/`<strong style="color: yellow;">timekeepnigId</strong>`/delete`
>
> Method : <strong style="color: red;">DELETE</strong>
>
> <br>

| Param                                                 | Value                                     |
| ----------------------------------------------------- | ----------------------------------------- |
| <strong style="color: yellow;">timekeepnigId</strong> | `CC-346c849e-e04b-4cc8-b573-a051f84571c5` |

<div style="width: 100%; height: 10px; background: #185684;"></div>
<br>
<br>

# Cập nhật 1 bản ghi trong bảng Chấm công

> <br>
>
> URL (Local) : `http://localhost:8080/api/v1/timekeeping/`<strong style="color: yellow;">timekeepnigId</strong>`/update`
>
> Method : <strong style="color: red;">PUT</strong>
>
> <br>

| Param                                                 | Value                                     |
| ----------------------------------------------------- | ----------------------------------------- |
| <strong style="color: yellow;">timekeepnigId</strong> | `CC-346c849e-e04b-4cc8-b573-a051f84571c5` |

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

<div style="width: 100%; height: 10px; background: #185684;"></div>
