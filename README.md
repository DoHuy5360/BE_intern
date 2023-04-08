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

```

### Xem 1 nhân viên

Url: `https://be-intern.onrender.com/api/v2/employee/{id}/show`

Method: `GET`

Body:

```json

```

Response:

```json

```

### Tạo 1 nhân viên + 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/employee/store`

Method: `POST`

Body:

```json

```

Response:

```json

```

### Tạo nhiều nhân viên + nhiều tài khoản

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

```

### Xóa 1 nhân viên

Url: `https://be-intern.onrender.com/api/v2/employee/{id}/delete`

Method: `DELETE`

Body:

```json

```

Response:

```json

```

### Cập nhật 1 nhân viên

Url: `https://be-intern.onrender.com/api/v2/employee/{id}/update`

Method: `PUT`

Body:

```json

```

Response:

```json

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

```

### Cập nhật thông tin nhân viên (bản thân)

Url: `https://be-intern.onrender.com/api/v2/employee/update-self`

Method: `PUT`

Body:

```json

```

Response:

```json

```

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

```

### Cập nhật 1 trụ sỏ

Url: `https://be-intern.onrender.com/api/v2/headquarter/{id}/update`

Method: `PUT`

Body:

```json

```

Response:

```json

```

### Xóa 1 trụ sỏ

Url: `https://be-intern.onrender.com/api/v2/headquarter/{id}/detele`

Method: `DELETE`

Body:

```json

```

Response:

```json

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

```

### Xem tất cả lịch làm việc + thông tin nhân viên + trụ sở

Url: `https://be-intern.onrender.com/api/v2/workschedule/all-information`

Method: `GET`

Body:

```json

```

Response:

```json

```

### Xem 1 lịch làm việc

Url: `https://be-intern.onrender.com/api/v2/workschedule/{id}/show`

Method: `GET`

Body:

```json

```

Response:

```json

```

## Quyền truy cập: Manager & Employee

### Tạo 1 lịch làm việc

Url: `https://be-intern.onrender.com/api/v2/workschedule/store`

Method: `POST`

Body:

```json

```

Response:

```json

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

```

### Cập nhật lịch làm việc (bản thân)

Url: `https://be-intern.onrender.com/api/v2/workschedule/{id}/update`

Method: `PUT`

Body:

```json

```

Response:

```json

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

```

### Xem 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/{id}`

Method: `GET`

Body:

```json

```

Response:

```json

```

### Tạo 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/store`

Method: `POST`

Body:

```json

```

Response:

```json

```

### Cập nhật 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/{id}/update`

Method: `PUT`

Body:

```json

```

Response:

```json

```

### Xóa 1 tài khoản

Url: `https://be-intern.onrender.com/api/v2/account/{id}/delete`

Method: `DELETE`

Body:

```json

```

Response:

```json

```

## Quyền truy cập: Manager & Employee

### Đổi mật khẩu

Url: `https://be-intern.onrender.com/api/v2/account/reset-password`

Method: `PUT`

Body:

```json

```

Response:

```json

```
