package com.wirlessqa.content.provider;

import android.net.Uri;

/**
 * Profile�����ڴ�Ÿ��ֳ���
 * 
 * @author www.wirelessqa.com 2013-2-26 ����11:01:46
 */
public class Profile {

    public static final String TABLE_NAME        = "profile";                                        // �������

    public static final String COLUMN_ID         = "_id";                                            // �б�һ,_ID,�Զ�����

    public static final String COLUMN_NAME       = "name";                                           // �б��������

    public static final String AUTOHORITY        = "com.wirlessqa.content.provider";
    public static final int    ITEM              = 1;
    public static final int    ITEM_ID           = 2;

    // ����������������ڼ������ͣ���ôMIME�����ַ���Ӧ����vnd.android.cursor.dir/��ͷ��
    public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/profile";
    // ���Ҫ�������������ڷǼ����������ݣ���ôMIME�����ַ���Ӧ����vnd.android.cursor.item/��ͷ
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/profile";

    public static final Uri    CONTENT_URI       = Uri.parse("content://" + AUTOHORITY + "/profile");
}
