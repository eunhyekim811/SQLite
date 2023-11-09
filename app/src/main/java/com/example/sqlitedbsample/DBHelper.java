package com.example.sqlitedbsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {  //sqliteopenhelper : 추상클래스
    public static final int DATABASE_VERSION=1;

    //생성자
    public DBHelper(@Nullable Context context){
        super(context, "memodb", null, DATABASE_VERSION);
    }
    //만들고자 하는 db 이름 : memodb

    @Override
    public void onCreate(SQLiteDatabase db) {  //추상 메소드, 최초 실행 시 딱 한 번만 호출
        //테이블(이름 : tb_memo) 만들기
        String memoSQL="create table tb_memo (" +
                "_id integer primary key autoincrement," + //id 정수, primary key, autoincrement : 데이터 입력마다 값이 자동으로 하나씩 증가
                "title," +
                "content)";
        db.execSQL(memoSQL);  //insert
    }

    //추상 메소드
    //oldversion과 newversion이 다를 때만 호출
    //버전 상수 여러개 -> 여러 if를 통해 버전별로 실행하고 싶은 코드 따로 만들 수 있음
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  //기존의 테이블 삭제되고 새로운 테이블 생성(초기화)
        //oldversion : dbehlper 호출 시 기존에 가지고 있었던 네 번째 파라미터 값(DATABASE_VERSION)
        //newVersion : 2번째 실행 시 나오는 버전
            if(newVersion==DATABASE_VERSION){
                db.execSQL("drop table tb_memo");  //drop : delete table = 기존 만들었던 테이블을 지워라
                onCreate(db);  //강제 호출(자동 호출 시에는 딱 한 번만 최초 실행)
            }
    }
}
