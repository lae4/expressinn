<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">编辑快递</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="所属货架" prop="shelfId">
                <Select v-model="form.shelfId" clearable placeholder="请选择所属货架" style="width:570px">
                    <Option v-for="(item,index) in shelfList" :key="index" :value="item.id">{{ item.title }}</Option>
                </Select>
            </FormItem>
            <FormItem label="快递类型" prop="expressType">
                <Select v-model="form.expressType" clearable placeholder="请选择快递类型..." style="width:570px">
                    <Option value="生活用品">生活用品</Option>
                    <Option value="食品">食品</Option>
                    <Option value="数码设备">数码设备</Option>
                    <Option value="其他">其他</Option>
                </Select>
            </FormItem>
            <FormItem label="包装类型" prop="packageType">
                <Select v-model="form.packageType" clearable placeholder="请选择包装类型..." style="width:570px">
                    <Option v-for="(item,index) in typeList" :key="index" :value="item.id">{{ item.title }}</Option>
                </Select>
            </FormItem>
            <FormItem label="收件人" prop="receivingUser">
                <Input v-model="form.receivingUser" clearable show-word-limit maxlength="240" placeholder="请输入收件人..." style="width:570px" />
            </FormItem>
            <FormItem label="收件人电话" prop="receivingMobile">
                <Input v-model="form.receivingMobile" clearable show-word-limit maxlength="240" placeholder="请输入收件人电话..." style="width:570px" />
            </FormItem>
            <FormItem label="收件地址" prop="receivingAddress">
                <Input v-model="form.receivingAddress" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入收件地址..." style="width:570px" />
            </FormItem>
            <FormItem label="发件人" prop="sendUser">
                <Input v-model="form.sendUser" clearable show-word-limit maxlength="240" placeholder="请输入发件人..." style="width:570px" />
            </FormItem>
            <FormItem label="发件人电话" prop="sendMobile">
                <Input v-model="form.sendMobile" clearable show-word-limit maxlength="240" placeholder="请输入发件人电话..." style="width:570px" />
            </FormItem>
            <FormItem label="发件地址" prop="sendAddress">
                <Input v-model="form.sendAddress" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入发件地址..." style="width:570px" />
            </FormItem>
            <FormItem label="快递状态" prop="status">
                <Select v-model="form.status" clearable placeholder="请选择快递状态..." style="width:570px">
                    <Option value="正常">正常</Option>
                    <Option value="失效">失效</Option>
                </Select>
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editExpress,
    getExpressShelfList,
    getPackageTypeList
} from "./api.js";
export default {
    name: "edit",
    components: {},
    props: {
        data: Object
    },
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                shelfId: "",
                shelfName: "",
                areaId: "",
                areaName: "",
                expressType: "",
                packageType: "",
                receivingUser: "",
                receivingMobile: "",
                receivingAddress: "",
                sendUser: "",
                sendMobile: "",
                sendAddress: "",
                status: "",
            },
            // 表单验证规则
            formValidate: {},
            shelfList: [],
            typeList: []
        };
    },
    methods: {
        init() {
            this.getExpressShelfListFx();
            this.getPackageTypeListFx();
            this.handleReset();
            this.form = this.data;
        },
        getExpressShelfListFx() {
            var that = this;
            that.shelfList = [];
            getExpressShelfList().then(res => {
                if (res.success) {
                    that.shelfList = res.result;
                }
            })
        },
        getPackageTypeListFx() {
            var that = this;
            that.typeList = [];
            getPackageTypeList().then(res => {
                if (res.success) {
                    that.typeList = res.result;
                }
            })
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editExpress(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
// 建议引入通用样式 具体路径自行修改 可删除下面样式代码
// @import "../../../styles/single-common.less";
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
