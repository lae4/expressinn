import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getExpressAreaOne = (params) => {
    return getRequest('/expressArea/getOne', params)
}
export const getExpressAreaList = (params) => {
    return getRequest('/expressArea/getByPage', params)
}
export const getExpressAreaCount = (params) => {
    return getRequest('/expressArea/count', params)
}
export const addExpressArea = (params) => {
    return postRequest('/expressArea/insert', params)
}
export const editExpressArea = (params) => {
    return postRequest('/expressArea/update', params)
}
export const addOrEditExpressArea = (params) => {
    return postRequest('/expressArea/insertOrUpdate', params)
}
export const deleteExpressArea = (params) => {
    return postRequest('/expressArea/delByIds', params)
}