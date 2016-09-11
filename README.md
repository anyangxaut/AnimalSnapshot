# Animal Snapshot
[![Build Status](https://travis-ci.org/anyangxaut/AnimalSnapshot.svg?branch=master)](https://travis-ci.org/anyangxaut/AnimalSnapshot)


## 使用说明

运行单元测试：gradle test


## 主要文件说明

    (1) AnimalSnapshot类：包含String getSnapShot(String historyData, String id)方法，返回某一特定时刻的所有动物的坐标快照；

    (2) DataFormatWithOriginalCoordinates类：将字符串格式的historyData转换为LinkedHashMap<String snapshotId, Snapshot snapshot>
    形式，并不对其中动物坐标进行计算，即仅将原始字符串数据转换为面向对象形式进行存储；

    (3) DataFormatWithNewestCoordinates类：根据DataFormatWithOriginalCoordinates类转化完成的LinkedHashMap<String snapshotId,
    Snapshot snapshot>格式数据，遍历所有时刻快照数据并计算所有动物当前时刻坐标，即对应任意时刻的Snapshot均存储有之前出现的所有动物
    的当前时刻坐标信息，在该过程中包括了坐标数据冲突检测，当检测到数据冲突便抛出ConflictFoundException异常；

    (4) InputVerification类：验证输入数据historyData的有效性，当historyData的格式不符合要求时抛出InvalidFormatException异常；

    (5) SnapshotPrinter类：格式化输出；􀀁􀂗􀂠􀀄􀃧􀁭􀀍􀁺􀀗􀁢􀃘􀀟


Github：https://github.com/anyangxaut/AnimalSnapshot


