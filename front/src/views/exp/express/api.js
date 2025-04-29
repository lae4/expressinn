import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getExpressOne = (params) => {
    return getRequest('/express/getOne', params)
}
export const getExpressList = (params) => {
    return getRequest('/express/getByPage', params)
}
export const getExpressCount = (params) => {
    return getRequest('/express/count', params)
}
export const addExpress = (params) => {
    return postRequest('/express/insert', params)
}
export const editExpress = (params) => {
    return postRequest('/express/update', params)
}
export const addOrEditExpress = (params) => {
    return postRequest('/express/insertOrUpdate', params)
}
export const deleteExpress = (params) => {
    return postRequest('/express/delByIds', params)
}
export const getExpressShelfList = (params) => {
    return getRequest('/expressShelf/getAll', params)
}
export const getPackageTypeList = (params) => {
    return getRequest('/packageType/getAll', params)
}