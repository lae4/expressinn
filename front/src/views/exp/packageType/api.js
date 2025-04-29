import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getPackageTypeOne = (params) => {
    return getRequest('/packageType/getOne', params)
}
export const getPackageTypeList = (params) => {
    return getRequest('/packageType/getByPage', params)
}
export const getPackageTypeCount = (params) => {
    return getRequest('/packageType/count', params)
}
export const addPackageType = (params) => {
    return postRequest('/packageType/insert', params)
}
export const editPackageType = (params) => {
    return postRequest('/packageType/update', params)
}
export const addOrEditPackageType = (params) => {
    return postRequest('/packageType/insertOrUpdate', params)
}
export const deletePackageType = (params) => {
    return postRequest('/packageType/delByIds', params)
}